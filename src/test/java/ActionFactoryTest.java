
import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Actions.SpawnAction;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveWrapper;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Player.CluelessAI;
import nl.hanze.hive.Units.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    // Requirement 4b, 4c, 4d
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

    // Requirement 4e
    @Test
    public void firstFourMovesShouldIncludeQueenBee() {
        List<Action> spawns = ActionFactory.getSpawnActions(white);
        HiveWrapper game = HiveGameFactory.getNew();
        game.setPlayerAI(Hive.Player.WHITE, white);
        game.setPlayerAI(Hive.Player.BLACK, black);
        game.applyAction(spawns.get(1)); // Beetle
        spawns = ActionFactory.getSpawnActions(white);
        game.applyAction(spawns.get(2)); // Beetle 2
        spawns = ActionFactory.getSpawnActions(white);
        game.applyAction(spawns.get(1)); // SoldierAnt
        spawns = ActionFactory.getSpawnActions(white);
        // These should all be queen, since we played 3 tiles but no queen
        for(Action a: spawns) {
            assertEquals(a.getUnit().getTile(), new QueenBee(Hive.Player.WHITE).getTile());
        }
    }

//    @Test
//    public void playerNeedsQueenBeforeMoving() {
//        HiveWrapper game = HiveGameFactory.getNew();
//        Actor white = new CluelessAI(Hive.Player.WHITE);
//        Actor black = new CluelessAI(Hive.Player.BLACK);
//        game.setPlayerAI(Hive.Player.WHITE, white);
//        game.setPlayerAI(Hive.Player.BLACK, black);
//
//        List<Action> spawnActions = ActionFactory.getSpawnActions(white);
//        game.applyAction(spawnActions.get(0)); // W Queen 0,0
//        spawnActions = ActionFactory.getSpawnActions(black);
//        game.applyAction(spawnActions.get(1)); // B Queen 0,-1
//        spawnActions = ActionFactory.getSpawnActions(white);
//        game.applyAction(spawnActions.get(4)); // W Ant 0,1
//        spawnActions = ActionFactory.getSpawnActions(black);
//        game.applyAction(spawnActions.get(1)); // B Ant -2,1
//        List<Action> moveActions = ActionFactory.getMoveActions(black);
//        System.out.println(moveActions);
//    }



    // Requirement 6c
    @Test
    public void unitShouldStayWithHive() {
        GameBoard board = HiveGameFactory.getNew().getBoard();
        board.get(-1,0).acceptUnit(new SoldierAnt(white.colour));
        board.get(0,-1).acceptUnit(new SoldierAnt(white.colour));
        board.get(1,-1).acceptUnit(new SoldierAnt(white.colour));
        board.get(1,0).acceptUnit(new SoldierAnt(white.colour));
        board.get(-1,1).acceptUnit(new Beetle(black.colour));
        List<Action> moves = ActionFactory.getMoveActions(black);
    }

    // Requirement 4a
    @Test
    public void playerShouldHaveLimitedTiles() {
        List<Action> spawns = ActionFactory.getSpawnActions(white);
        // Move one is queenbee
        HiveWrapper game = HiveGameFactory.getNew();
        game.setPlayerAI(Hive.Player.WHITE, white);
        game.setPlayerAI(Hive.Player.BLACK, black);

        game.applyAction(spawns.get(0));
        spawns = ActionFactory.getSpawnActions(white);
        // Queenbee should be gone from our available moves
        for(Action a: spawns) {
            assertNotEquals(a.getUnit().getTile(), new QueenBee(Hive.Player.BLACK).getTile());
        }
    }

}
