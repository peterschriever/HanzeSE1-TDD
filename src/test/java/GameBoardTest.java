import Game.Field;
import Game.GameBoard;
import Game.Pair;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;


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
        HashMap<Pair<Integer, Integer>, Field> neighbours = gb.getNeighboursForField(0, 0);
        assertEquals("Neighbour1 should equal neighbours.get(1, 0)", neighbour1, neighbours.get(new Pair<>(1, 0)));
        assertEquals("Neighbour2 should equal neighbours.get(0, 1)", neighbour2, neighbours.get(new Pair<>(0, 1)));
    }
}
