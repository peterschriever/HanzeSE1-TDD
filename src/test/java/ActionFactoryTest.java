
import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Actions.SpawnAction;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Player.CluelessAI;
import nl.hanze.hive.Units.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ActionFactoryTest {
    private Actor white;
    private Actor black;

    @Before
    public void before() {
        white = new CluelessAI(Hive.Player.WHITE);
        black = new CluelessAI(Hive.Player.BLACK);
    }
    @Test
    public void testAvailableFields() {
        GameBoard board = HiveGameFactory.getNew().getBoard();
        board.get(0,0).acceptUnit(new SoldierAnt(white.colour));
        board.get(-1, 0).acceptUnit(new SoldierAnt(black.colour));
        ArrayList<Field> available_fields = new ArrayList<>();
        available_fields.add(board.get(1, -1));
        available_fields.add(board.get(1, 0));
        available_fields.add(board.get(0, 1));
        ArrayList<Field> checked_fields = ActionFactory.getSpawnFields(white);
        assertEquals("Available fields should only contain 1,0 & 1,-1 & 0,1", available_fields, checked_fields);
    }

    @Test
    public void testSpawnActions() {
        GameBoard board = HiveGameFactory.getNew().getBoard();
        board.get(0,0).acceptUnit(new QueenBee(white.colour));
        white.queenbee--;
        board.get(-1, 0).acceptUnit(new SoldierAnt(black.colour));
        board.get(+1, -1).acceptUnit(new SoldierAnt(black.colour));
        board.get(0, -1).acceptUnit(new SoldierAnt(black.colour));
        List<Action> checked_fields = ActionFactory.getSpawnActions(white);
        List<Action> test = new ArrayList<>();
        test.add(new SpawnAction(new SoldierAnt(white.colour), new Coord(0, 1)));
        test.add(new SpawnAction(new Beetle(white.colour), new Coord(0, 1)));
        test.add(new SpawnAction(new Spider(white.colour), new Coord(0, 1)));
        test.add(new SpawnAction(new GrassHopper(white.colour), new Coord(0, 1)));
        assertEquals("Available actions should equal test actions", test, checked_fields);
    }

    // Requirement 6c
    @Test
    public void unitShouldStayWithHive() {
        GameBoard board = HiveGameFactory.getNew().getBoard();
        board.get(-1,0).acceptUnit(new SoldierAnt(white.colour));
        board.get(0,-1).acceptUnit(new SoldierAnt(white.colour));
        board.get(1,-1).acceptUnit(new SoldierAnt(white.colour));
        board.get(1,0).acceptUnit(new SoldierAnt(white.colour));
        board.get(-1,1).acceptUnit(new Spider(black.colour));
        List<Action> moves = ActionFactory.getMoveActions(black);
        System.out.println(moves);
    }
}
