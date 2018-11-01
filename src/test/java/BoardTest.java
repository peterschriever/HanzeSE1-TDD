import AI.CluelessAI;
import AI.PlayerAI;
import Actions.Action;
import Actions.SpawnAction;
import Game.Hive;
import Game.HiveGame;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BoardTest {
    private HiveGame game;

    @Before public void setupGame() {
        PlayerAI playerWhite = new CluelessAI();
        PlayerAI playerBlack = new CluelessAI();
        this.game = new HiveGame(playerWhite, playerBlack);
    }

    @Test public void boardShouldApplySpawnAction() {
//        Action action = new SpawnAction(Hive.Player.WHITE);
    }

}
