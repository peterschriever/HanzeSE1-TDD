
import Actions.Action;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.Coord;
import Game.Hive;
import Game.HiveGame;
import Game.HiveGameFactory;
import Player.CluelessAI;
import Units.Beetle;
import Units.GameUnit;
import Units.QueenBee;
import Units.SoldierAnt;
import org.junit.Before;
import org.junit.Test;
import Game.Hive.Colour;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class GameUnitTest {
    private HiveGame game;

    @Before
    public void setupGame() {
        this.game = HiveGameFactory.getInstance();
        game.setPlayerAI(Colour.WHITE, new CluelessAI(Colour.WHITE));
        game.setPlayerAI(Colour.BLACK, new CluelessAI(Colour.BLACK));

        GameUnit queenBee = new QueenBee(Colour.WHITE);
        Action spawnBee = new SpawnAction(queenBee, new Coord(0, 0));
        game.getBoard().applyAction(spawnBee);

        GameUnit ant = new SoldierAnt(Colour.BLACK);
        Action spawnAnt = new SpawnAction(ant, new Coord(-1, 0));
        game.getBoard().applyAction(spawnAnt);

        GameUnit beetle = new Beetle(Colour.WHITE);
        Action spawnBeetle = new SpawnAction(beetle, new Coord(0, 1));
        game.getBoard().applyAction(spawnBeetle);

        GameUnit ant2 = new SoldierAnt(Colour.BLACK);
        Action spawnAnt2 = new SpawnAction(ant2, new Coord(-1, -1));
        game.getBoard().applyAction(spawnAnt2);
    }

    @Test
    public void gameUnitShouldPersistPlayer() {
        Hive.Colour colour = Hive.Colour.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Hive.Colour.BLACK);
    }

    @Test
    public void beetleShouldGenerateValidPaths() {
        // 0, 1 is the x, y Pair that ActionFactory would normally apply to the method
        GameUnit beetle = game.getBoard().get(0, 1).getUnits().peek();
        List<MoveAction> paths = beetle.generateValidPaths(0, 1);
        assertNotNull("paths should not be null", paths);
    }

}
