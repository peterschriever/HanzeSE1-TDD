import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HiveGameTest {

    @Test
    public void playShouldSpawnUnitOnBoard() {
        HiveGame game = new HiveGame();
        try {
            game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }
        Field field = game.wrapper.getBoard().get(0, 0);
        assertEquals(
            "unit tile is QUEEN_BEE",
            Hive.Tile.QUEEN_BEE,
            field.getUnits().peek().getTile()
        );
    }

    @Test(expected = Hive.IllegalMove.class)
    public void playShouldThrowExceptionOnSameCoord() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        // play on 0,0 twice to trigger exception
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
    }

    @Test(expected = Hive.IllegalMove.class)
    public void playShouldThrowExceptionWhenBadCoord() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, 0, -10);
    }

}
