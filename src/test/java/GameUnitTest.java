
import Actions.Action;
import Actions.SpawnAction;
import Game.Hive;
import Game.HiveGame;
import Game.HiveGameFactory;
import Game.Pair;
import Player.CluelessAI;
import Units.Beetle;
import Units.GameUnit;
import Units.QueenBee;
import Units.SoldierAnt;
import org.junit.Before;
import org.junit.Test;
import Game.Hive.Colour;

import static junit.framework.TestCase.assertEquals;

public class GameUnitTest {
    private HiveGame game;

    @Before
    public void setupGame() {
        this.game = HiveGameFactory.getInstance();
        game.setPlayerAI(Colour.WHITE, new CluelessAI(Colour.WHITE));
        game.setPlayerAI(Colour.BLACK, new CluelessAI(Colour.BLACK));

        GameUnit beetle = new Beetle(Colour.WHITE);
        Action spawnBeetle = new SpawnAction(beetle, new Pair<>(0, 0));
        game.getBoard().applyAction(spawnBeetle);

        GameUnit ant = new SoldierAnt(Colour.BLACK);
        Action spawnAnt = new SpawnAction(ant, new Pair<>(-1, 0));
        game.getBoard().applyAction(spawnAnt);
    }

    @Test
    public void gameUnitShouldPersistPlayer() {
        Hive.Colour colour = Hive.Colour.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Hive.Colour.BLACK);
    }

    @Test
    public void beetleShouldGenerateValidPaths() {

    }

}
