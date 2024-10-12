package online.glees.numble.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import online.glees.numble.model.Attempt;
import online.glees.numble.model.Response;
import online.glees.numble.utils.GameMapper;

/**
 * Game
 */
@Service
public class Game {

    private GameMapper gameMapper;

    final public int MAX_TRIES = 4;

    private List<Group> groupings;

    public Game(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
        this.groupings = new ArrayList<Group>(Arrays.asList(
            new Group("Prime numbers", 7, 11, 19, 43),
            new Group("Squares", 9, 49, 81, 100),
            new Group("Beginning numbers of pi rounded", 3,1,4,2),
            new Group("Possible end dates of a month", 28,29,30,31)
        ));
    }

    public Response play(Attempt attempt) {
        return switch (attempt.getAttempt()) {
            case 0 -> newGame();
            case MAX_TRIES -> gameOver();
            case -1 -> gameEnded(attempt);
            default -> round(attempt);
        };
    }

    private Response newGame() {
        Response response = new Response();
        response.setAttempt(1);
        List<String> values = groupings.stream()
                .flatMap(group -> group.getValues().stream())
                .map(i -> i.toString()).toList();
        Collections.shuffle(values);
        response.setValues(values);
        response.setId(UUID.randomUUID().toString());
        return response;
    }

    private Response round(Attempt attempt) {

        List<Integer> guess = attempt.getGuesses().getValues().stream()
            .map(v -> Integer.valueOf(v)).toList();

        List<Group> correctGuesses = gameMapper.guessToGroup(attempt.getPreviousCorrectGuesses());

        groupings.forEach(group -> {
            if (listCompare(group.getValues(), guess)) {
                correctGuesses.add(group);
            }
        });
    
        Response response = new Response();
        response.setId(attempt.getId());
        response.setCorrect(gameMapper.groupToGuess(correctGuesses));
        response.setValues(groupings.stream()
            .flatMap(group -> group.getValues().stream())
            .map(i -> i.toString()).toList());
        response.setAttempt(attempt.getAttempt() + 1);
        return response;
    }

    private Response gameOver() {
        return new Response();
    }

    private Response gameEnded(Attempt attempt) {
        Response response = round(attempt);
        response.setAttempt(-1);
        return response;
    }

    private boolean listCompare(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }
}
