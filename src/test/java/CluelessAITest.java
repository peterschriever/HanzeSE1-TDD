import Player.Actor;
import Player.CluelessAI;
import Actions.Action;
import Actions.ActionFactory;
import Game.Hive;
import Game.HiveWrapper;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CluelessAITest {
    private HiveWrapper game;
    private Actor playerWhite;
    private Actor playerBlack;

    @Before
    public void setupGame() {
        playerWhite = new CluelessAI(Hive.Player.WHITE);
        playerBlack = new CluelessAI(Hive.Player.BLACK);
        this.game = new HiveWrapper(playerWhite, playerBlack);
    }

    @Test
    public void aiShouldChooseValidMove() {
        // chooseAction() uses RNG for its decision making, so we test it 1000 times
        for (int i = 0; i < 100; i++) {
            Action action = game.getPlayerAI(Hive.Player.WHITE).chooseAction();
            assertThat("AI Should return an Action.class", action, instanceOf(Action.class));
            List<Action> validActions = ActionFactory.generateValidActions(playerWhite);
            assertThat("Chosen action should be in validActions", validActions, hasItem(action));
        }
    }

}