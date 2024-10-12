package online.glees.numble.commandline;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import org.springframework.shell.standard.ShellComponent;
// import org.springframework.shell.standard.ShellMethod;

import lombok.RequiredArgsConstructor;
import online.glees.numble.model.Attempt;
import online.glees.numble.model.Guess;
import online.glees.numble.model.Response;
import online.glees.numble.service.Game;

/**
 * UserInterface
 */
// @ShellComponent
@RequiredArgsConstructor
public class UserInterface {

    private Game game;

    final private int GRID = 4;

    // @ShellMethod(key = "numble")
    public void run() {
        System.out.println("Welcome to Numble!");
        System.out.println("Group numbers that share a common thread.");
        System.out.println("Please enter your guess in the format w,x,y,z");
        Response response = game.play(new Attempt());
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < game.MAX_TRIES; i++) {
            System.out.println("So far you have " + response.getCorrect().size() + " correct guesses:");
            response.getCorrect().forEach(System.out::println); 
            printGrid(response.getValues());
            System.out.println("Guess " + response.getAttempt()+1 + ":");
            List<Integer> numGuess = new ArrayList<>();
            while(scan.hasNextInt()){
                numGuess.add(scan.nextInt());
           }
            Attempt attempt = new Attempt();
            attempt.setId(response.getId());
            attempt.setPreviousCorrectGuesses(response.getCorrect());
            Guess guess = new Guess();
            guess.setValues(numGuess.stream().map(n -> n.toString()).toList());
            attempt.setGuesses(guess);
            game.play(attempt);
        }

        scan.close();
    }

    private void printGrid(List<String> values) {
        for (int i = 0; i < GRID; i++) {
            for (int j = 0; j < GRID; j++) {
                System.out.println(values.get((i * GRID) + j));
            }
        }
    }
}
