import Game.Coord;
import Game.Field;
import Game.GameBoard;
import Game.Hive;
import Units.Beetle;
import Units.GrassHopper;
import Units.QueenBee;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class GameBoardTest {
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

    @Test
    public void playerShouldMakeFirstMove() {
        GameBoard gb = new GameBoard();
        gb.get(0,0).acceptUnit(new QueenBee(Hive.Colour.BLACK));
        gb.get(0,1).acceptUnit(new QueenBee(Hive.Colour.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
        gb.get(2,1).acceptUnit(new GrassHopper(Hive.Colour.BLACK));
        assertFalse("Hive isn't a swarm", gb.isSwarm());
        gb.get(1,1).acceptUnit(new GrassHopper(Hive.Colour.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
    }
    @Test
    public void boardShouldReturnOccupiedFields() {
        GameBoard gb = new GameBoard();
        assertEquals("Size of board units should be zero", 0, gb.getFieldsWithUnits().size());
        gb.get(0,0).acceptUnit(new QueenBee(Hive.Colour.BLACK));
        assertEquals("Size of board units should be one", 1, gb.getFieldsWithUnits().size());
        gb.get(0,0).acceptUnit(new Beetle(Hive.Colour.WHITE));
        assertEquals("Size of board units should be one", 1, gb.getFieldsWithUnits().size());
    }
}
