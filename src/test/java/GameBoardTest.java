import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Units.Beetle;
import nl.hanze.hive.Units.GrassHopper;
import nl.hanze.hive.Units.QueenBee;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GameBoardTest {

    // Requirement 2a
    @Test
    public void boardShouldAddFields() {
        GameBoard gb = new GameBoard();
        Field test = gb.addNewField(0, 0);
        Field compare = gb.get(0, 0);
        assertEquals("Fields should be equal", test, compare);
        assertEquals("Size of board should be one", 1, gb.size());
        test = gb.get(0, 1);
        compare = gb.get(0, 1);
        assertEquals("Implicitly generated field objects should be equal", test, compare);
        gb.addNewField(0, 2);
        assertEquals("Size of board should be three", 3, gb.size());
    }

    @Test
    public void testNeighbours() {
        GameBoard gb = new GameBoard();
        Field neighbour1 = gb.addNewField(1, 0);
        Field neighbour2 = gb.addNewField(0, 1);
        HashMap<Coord, Field> neighbours = gb.getNeighboursForField(0, 0);
        assertEquals("Neighbour1 should equal neighbours.get(1, 0)", neighbour1, neighbours.get(new Coord(1, 0)));
        assertEquals("Neighbour2 should equal neighbours.get(0, 1)", neighbour2, neighbours.get(new Coord(0, 1)));
    }

    // Requirement 2b
    @Test
    public void fieldHasSixNeighbours() {
        GameBoard gb = new GameBoard();
        HashMap<Coord, Field> neighbours = gb.getNeighboursForField(0, 0);
        assertEquals("Any field should have 6 neighbours", neighbours.size(), 6);
    }

    @Test
    public void playerShouldMakeFirstMove() {
        GameBoard gb = new GameBoard();
        gb.get(0,0).acceptUnit(new QueenBee(Hive.Player.BLACK));
        gb.get(0,1).acceptUnit(new QueenBee(Hive.Player.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
        gb.get(2,1).acceptUnit(new GrassHopper(Hive.Player.BLACK));
        assertFalse("Hive isn't a swarm", gb.isSwarm());
        gb.get(1,1).acceptUnit(new GrassHopper(Hive.Player.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
    }

    // Requirement 2c
    @Test
    public void boardShouldReturnOccupiedFields() {
        GameBoard gb = new GameBoard();
        assertEquals("Size of board units should be zero", 0, gb.getFieldsWithUnits().size());
        gb.get(0,0).acceptUnit(new QueenBee(Hive.Player.BLACK));
        assertEquals("Size of board units should be one", 1, gb.getFieldsWithUnits().size());
        gb.get(0,0).acceptUnit(new Beetle(Hive.Player.WHITE));
        assertEquals("Size of board units should be one", 1, gb.getFieldsWithUnits().size());
    }
}
