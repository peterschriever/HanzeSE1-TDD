import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveGame;
import nl.hanze.hive.Units.QueenBee;
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
    public void playShouldThrowOnSameCoord() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        // play on 0,0 twice to trigger exception
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
    }

    @Test(expected = Hive.IllegalMove.class)
    public void playShouldThrowWhenBadCoord() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, 0, -10);
    }

    // Anti-requirement, should not be limited to just 0,0
    @Test
    public void firstPlayShouldWorkOnManyCoords() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.GRASSHOPPER, 1, 2);
        Hive.Tile actual = game.wrapper.getBoard().get(1, 2).getUnits().peek().getTile();
        assertEquals("Tiles should be the same", Hive.Tile.GRASSHOPPER, actual);
    }

    // Requirement 6a, 5
    @Test
    public void moveShouldBePossible() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.QUEEN_BEE, 1, 0); // black plays queen 1, 0
        game.move(0, 0, 0, 1); // white moves queen 0, 0 -> 0, 1
        Hive.Tile actual = game.wrapper.getBoard().get(0, 1).getUnits().peek().getTile();
        assertEquals("Tiles should be the same", Hive.Tile.QUEEN_BEE, actual);

        game.move(1, 0, 1, 1);
        actual = game.wrapper.getBoard().get(1, 1).getUnits().peek().getTile();
        assertEquals("Tiles should be the same", Hive.Tile.QUEEN_BEE, actual);
    }

    // Requirement 5a
    @Test(expected = Hive.IllegalMove.class)
    public void moveShouldThrowForOpponentMove() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.BEETLE, 1, 0); // black plays beetle 1, 0
        game.move(1, 0, 1, 1); // white tries to move black's unit (illegal)
    }

    @Test(expected = Hive.IllegalMove.class)
    public void moveShouldThrowForInvalidTo() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.BEETLE, 1, 0); // black plays beetle 1, 0
        game.move(0, 0, 10, 10); // white moves queen 0, 0 -> 10, 10 (illegal)
    }

    @Test(expected = Hive.IllegalMove.class)
    public void moveShouldThrowForInvalidFrom() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.BEETLE, 1, 0); // black plays beetle 1, 0
        game.move(10, 10, 0, 1); // white tries to move unknown field (illegal)
    }

    // Requirement 5b
    @Test(expected = Hive.IllegalMove.class)
    public void moveShouldBeImpossibleWithoutQueen() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.BEETLE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.QUEEN_BEE, 1, 0); // black plays queen 1, 0
        game.move(0, 0, 0, 1); // white moves beetle (illegal, no queen)
    }

    // Requirement 12
    @Test
    public void passingShouldBePossible() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); // white plays queen 0, 0
        game.play(Hive.Tile.QUEEN_BEE, 1, 0); // black plays queen 1, 0
        game.move(0, 0, 0, 1); // white moves queen 0, 0 -> 0, 1
        game.play(Hive.Tile.SOLDIER_ANT, 2, 0); // black plays ant 1, 1
        game.move(0, 1, 0, 0); // white moves queen 0, 1 -> 0, 0
        game.move(2, 0, -1, 0); // black moves ant 1, 1 -> -1, -1

        game.pass(); // white has to pass
    }
    // Requirement 12
    @Test(expected = Hive.IllegalMove.class)
    public void passingShouldThrow() throws Hive.IllegalMove {
        HiveGame game = new HiveGame();
        game.pass();
    }

}
