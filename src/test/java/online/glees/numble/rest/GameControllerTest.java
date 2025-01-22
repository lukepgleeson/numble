package online.glees.numble.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import online.glees.numble.model.Attempt;
import online.glees.numble.service.Game;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GameControllerTest {

    @Autowired
    GameController gameController;

    @MockBean
    Game game;

    @Test
    void play() {
        //given
        Attempt attempt = new Attempt();
        //when
        gameController.play(attempt);
        //then
        verify(game, times(1)).play(attempt);
    }

}
