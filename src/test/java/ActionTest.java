import Actions.Action;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.Hive;
import Units.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ActionTest {

//    @Test public void actionShouldHavePlayer() {
//        GameUnit unit = new QueenBee(Hive.Player.WHITE);
//        Action action = new SpawnAction(Hive.Player.WHITE, unit);
//        assertEquals("action has player white", action.getPlayer(), Hive.Player.WHITE);
//        action = new SpawnAction(Hive.Player.BLACK, unit);
//        assertEquals("action has player black", action.getPlayer(), Hive.Player.BLACK);
//
//        action = new MoveAction(Hive.Player.WHITE, unit);
//        assertEquals("action has player white", action.getPlayer(), Hive.Player.WHITE);
//        action = new MoveAction(Hive.Player.BLACK, unit);
//        assertEquals("action has player black", action.getPlayer(), Hive.Player.BLACK);
//    }

    @Test
    public void spawnActionShouldHaveUnit() {
        Hive.Player player = Hive.Player.WHITE;
        GameUnit grassHopper = new GrassHopper(player);
        GameUnit queenBee = new QueenBee(player);
        GameUnit soldierAnt = new SoldierAnt(player);
        GameUnit spider = new Spider(player);
        GameUnit beetle = new Beetle(player);

        Action action = new SpawnAction(grassHopper);
        assertEquals("spawnAction has grassHopper", action.getUnit(), grassHopper);
        action = new SpawnAction(queenBee);
        assertEquals("spawnAction has queenBee", action.getUnit(), queenBee);
        action = new SpawnAction(soldierAnt);
        assertEquals("spawnAction has soldierAnt", action.getUnit(), soldierAnt);
        action = new SpawnAction(spider);
        assertEquals("spawnAction has spider", action.getUnit(), spider);
        action = new SpawnAction(beetle);
        assertEquals("spawnAction has beetle", action.getUnit(), beetle);
    }

}
