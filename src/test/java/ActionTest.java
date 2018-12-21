import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Actions.SpawnAction;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Units.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionTest {

    @Test
    public void spawnActionShouldHaveUnit() {
        Hive.Player colour = Hive.Player.WHITE;
        Coord rootCoords = new Coord(0, 0);
        GameUnit grassHopper = new GrassHopper(colour);
        GameUnit queenBee = new QueenBee(colour);
        GameUnit soldierAnt = new SoldierAnt(colour);
        GameUnit spider = new Spider(colour);
        GameUnit beetle = new Beetle(colour);

        Action action = new SpawnAction(grassHopper, rootCoords);
        assertEquals("spawnAction has grassHopper", action.getUnit(), grassHopper);
        action = new SpawnAction(queenBee, rootCoords);
        assertEquals("spawnAction has queenBee", action.getUnit(), queenBee);
        action = new SpawnAction(soldierAnt, rootCoords);
        assertEquals("spawnAction has soldierAnt", action.getUnit(), soldierAnt);
        action = new SpawnAction(spider, rootCoords);
        assertEquals("spawnAction has spider", action.getUnit(), spider);
        action = new SpawnAction(beetle, rootCoords);
        assertEquals("spawnAction has beetle", action.getUnit(), beetle);
    }

    @Test
    public void spawnActionShouldHaveCoordCoord() {
        GameUnit grassHopper = new GrassHopper(Hive.Player.WHITE);

        Coord coords = new Coord(0, 0);
        SpawnAction action = new SpawnAction(grassHopper, coords);
        assertEquals("spawnAction should have an x,y pair", action.getSpawnCoord(), coords);
    }

    @Test
    public void moveActionShouldHaveFromAndTo() {
        GameUnit soldierAnt = new SoldierAnt(Hive.Player.WHITE);
        Coord from = new Coord(0, 0);
        Coord to = new Coord(1, 0);

        MoveAction action = new MoveAction(soldierAnt, from, to);
        assertEquals("moveAction has from x,y pair", action.getFrom(), from);
        assertEquals("moveAction has to x,y pair", action.getTo(), to);
    }

}
