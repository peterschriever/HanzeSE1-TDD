
import Game.Hive;
import Game.HiveGame;
import Game.HiveGameFactory;
import Units.GameUnit;
import Units.QueenBee;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameUnitTest {
    private HiveGame game;

    @Before
    public void setupGame() {
        this.game = HiveGameFactory.getInstance();
    }

    @Test
    public void gameUnitShouldPersistPlayer() {
        Hive.Colour colour = Hive.Colour.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Hive.Colour.BLACK);
    }

}
