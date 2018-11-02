import Player.CluelessAI;
import Player.Player;
import Actions.Action;
import Actions.ActionFactory;
import Game.Hive;
import Game.HiveGame;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CluelessAIAITest {
    private HiveGame game;

    @Before
    public void setupGame() {
        Player playerWhite = new CluelessAI();
        Player playerBlack = new CluelessAI();
        this.game = new HiveGame(playerWhite, playerBlack);
    }

    @Test
    public void aiShouldChooseValidMove() {
        // chooseAction() uses RNG for its decision making, so we test it 100 times
        for (int i = 0; i < 100; i++) {
            Action action = game.getPlayerAI(Hive.Colour.WHITE).chooseAction();

            assertThat("AI Should return an Action.class", action, instanceOf(Action.class));
            List<Action> validActions = ActionFactory.generateValidActions(Hive.Colour.WHITE);
            assertThat("Chosen action should be in validActions", validActions, hasItem(action));
        }
    }

}