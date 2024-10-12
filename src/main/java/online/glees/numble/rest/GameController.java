package online.glees.numble.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import online.glees.numble.model.Attempt;
import online.glees.numble.model.Response;
import online.glees.numble.service.Game;

/**
 * GameController
 */
@RestController
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private Game game;

    @PostMapping("/play")
    Response play(@RequestBody Attempt attempt) {
        return game.play(attempt);
    }

}
