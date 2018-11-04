
import Actions.Action;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.Coord;
import Game.Hive;
import Game.HiveGame;
import Game.HiveGameFactory;
import Player.CluelessAI;
import Units.*;
import org.junit.Before;
import org.junit.Test;
import Game.Hive.Colour;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

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
    public void beetleShouldGenerateValidMoves() {
        // 0, 1 is the q, r Coord that ActionFactory would normally apply to the method
        GameUnit beetle = game.getBoard().get(0, 1).getUnits().peek();
        List<MoveAction> moves = beetle.generateValidMoves(new Coord(0, 1));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertEquals("moves should be size of 3", moves.size(), 3);
        assertEquals("moves should be as expected", str, "action{Beetle(WHITE), Coord{0, 1}, Coord{1, 0}}, action{Beetle(WHITE), Coord{0, 1}, Coord{0, 0}}, action{Beetle(WHITE), Coord{0, 1}, Coord{-1, 1}}");
    }

    @Test
    public void toStringShouldBeOfValidFormat() {
        String correct = "Beetle(WHITE)";
        assertEquals("Beetle should have correct toString format", new Beetle(Colour.WHITE).toString(), correct);
        correct = "QueenBee(WHITE)";
        assertEquals("QueenBee should have correct toString format", new QueenBee(Colour.WHITE).toString(), correct);
        correct = "SoldierAnt(WHITE)";
        assertEquals("SoldierAnt should have correct toString format", new SoldierAnt(Colour.WHITE).toString(), correct);
        correct = "Spider(WHITE)";
        assertEquals("Spider should have correct toString format", new Spider(Colour.WHITE).toString(), correct);
        correct = "GrassHopper(WHITE)";
        assertEquals("GrassHopper should have correct toString format", new GrassHopper(Colour.WHITE).toString(), correct);

        correct = "Beetle(BLACK)";
        assertEquals("Beetle should have correct toString format", new Beetle(Colour.BLACK).toString(), correct);
        correct = "QueenBee(BLACK)";
        assertEquals("QueenBee should have correct toString format", new QueenBee(Colour.BLACK).toString(), correct);
        correct = "SoldierAnt(BLACK)";
        assertEquals("SoldierAnt should have correct toString format", new SoldierAnt(Colour.BLACK).toString(), correct);
        correct = "Spider(BLACK)";
        assertEquals("Spider should have correct toString format", new Spider(Colour.BLACK).toString(), correct);
        correct = "GrassHopper(BLACK)";
        assertEquals("GrassHopper should have correct toString format", new GrassHopper(Colour.BLACK).toString(), correct);
    }

}
