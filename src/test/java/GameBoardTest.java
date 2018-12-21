import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveWrapper;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Player.CluelessAI;
import nl.hanze.hive.Units.Beetle;
import nl.hanze.hive.Units.GameUnit;
import nl.hanze.hive.Units.GrassHopper;
import nl.hanze.hive.Units.QueenBee;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

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

    // Requirement 5c
    @Test
    public void gameboardShouldRemainSwarm() {
        GameBoard gb = new GameBoard();
        gb.get(0,0).acceptUnit(new QueenBee(Hive.Player.BLACK));
        gb.get(0,1).acceptUnit(new QueenBee(Hive.Player.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
        gb.get(2,1).acceptUnit(new GrassHopper(Hive.Player.BLACK));
        assertFalse("Hive isn't a swarm", gb.isSwarm());
        gb.get(1,1).acceptUnit(new GrassHopper(Hive.Player.WHITE));
        assertTrue("Hive should be a swarm", gb.isSwarm());
    }

    // Requirement 5d
    @Test
    public void movingShouldMaintainOneSwarm() {
        HiveWrapper game = HiveGameFactory.getNew();
        Actor white = new CluelessAI(Hive.Player.WHITE);
        Actor black = new CluelessAI(Hive.Player.BLACK);
        game.setPlayerAI(Hive.Player.WHITE, white);
        game.setPlayerAI(Hive.Player.BLACK, black);

        List<Action> spawnActions = ActionFactory.getSpawnActions(white);
        GameUnit check = spawnActions.get(0).getUnit();
        game.applyAction(spawnActions.get(0)); // W Queen 0,0
        spawnActions = ActionFactory.getSpawnActions(black);
        game.applyAction(spawnActions.get(5)); // B Queen 0,-1
        spawnActions = ActionFactory.getSpawnActions(white);
        game.applyAction(spawnActions.get(4)); // W Ant 0,1
        spawnActions = ActionFactory.getSpawnActions(black);
        game.applyAction(spawnActions.get(0)); // B Ant -2,1
        // Try to pick up White queen
        assertFalse("Swarm is broken if white queen is moved", game.getBoard().isSwarmWithout(check));
        // QueenBee is not part of moveactions
        List<Action> moveActions = ActionFactory.getMoveActions(white);
        for(Action a: moveActions) {
            assertNotEquals("Tile is not QueenBee", a.getUnit().getTile(), Hive.Tile.QUEEN_BEE);
        }
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

    // Requirement 1a
    @Test
    public void gameShouldHaveTwoPlayers() {
        boolean white_found = false;
        boolean black_found = false;
        for(Hive.Player p: Hive.Player.values()) {
            if("BLACK".equals(p.toString())) black_found = true;
            if("WHITE".equals(p.toString())) white_found = true;
        }
        assertTrue("White is part of the player enum", white_found);
        assertTrue("Black is part of the player enum", black_found);
        assertEquals("Enum is 2 values long", 2, Hive.Player.values().length);
    }
}
