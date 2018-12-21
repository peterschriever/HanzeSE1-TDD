
import Actions.Action;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.*;
import Player.CluelessAI;
import Units.*;
import org.junit.Before;
import org.junit.Test;
import Game.Hive.Player;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameUnitTest {
    private HiveWrapper game;

    @Before
    public void setupGame() {
        this.game = HiveGameFactory.getNew();
        game.setPlayerAI(Player.WHITE, new CluelessAI(Hive.Player.WHITE));
        game.setPlayerAI(Hive.Player.BLACK, new CluelessAI(Player.BLACK));
    }

    private void setupUnit(GameUnit unit, Coord coord) {
        Action spawnUnit = new SpawnAction(unit, coord);
        game.applyAction(spawnUnit);
    }

    private void queenCanMove() {
        setupUnit(new QueenBee(Hive.Player.WHITE), new Coord(0, 0));
        setupUnit(new QueenBee(Player.BLACK), new Coord(-1, 0));
    }

    private void allUnitsExceptQueenCanMove() {
        setupUnit(new QueenBee(Hive.Player.WHITE), new Coord(0, 0));
        setupUnit(new Beetle(Player.WHITE), new Coord(1, 0));
        setupUnit(new SoldierAnt(Player.WHITE), new Coord(0, 1));
        setupUnit(new Spider(Hive.Player.WHITE), new Coord(1, -1));
        setupUnit(new SoldierAnt(Player.WHITE), new Coord(2, -1));
        setupUnit(new GrassHopper(Player.WHITE), new Coord(2, 0));

        setupUnit(new QueenBee(Player.BLACK), new Coord(-1, 0));
        setupUnit(new Beetle(Player.BLACK), new Coord(-2, 0));
        setupUnit(new GrassHopper(Hive.Player.BLACK), new Coord(-3, 0));
        setupUnit(new SoldierAnt(Hive.Player.BLACK), new Coord(-2, -1));
        setupUnit(new Spider(Player.BLACK), new Coord(-1, -1));
    }

    @Test
    public void gameUnitShouldPersistPlayer() {
        Player colour = Player.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Player.BLACK);
    }

    @Test
    public void beetleShouldGenerateValidMoves() {
        setupGame();
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit beetle = game.getBoard().get(1, 0).getUnits().peek();
        List<MoveAction> moves = beetle.generateValidMoves(new Coord(1, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 5", 5, moves.size());
        assertEquals("moves should be as expected", str, "Move Beetle(WHITE), from: Coord{1, 0}, to: Coord{2, -1}}, Move Beetle(WHITE), from: Coord{1, 0}, to: Coord{1, -1}}, Move Beetle(WHITE), from: Coord{1, 0}, to: Coord{2, 0}}, Move Beetle(WHITE), from: Coord{1, 0}, to: Coord{0, 0}}, Move Beetle(WHITE), from: Coord{1, 0}, to: Coord{0, 1}}");
    }

    @Test
    public void grassHopperShouldGenerateValidMoves() {
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit grassHopper = game.getBoard().get(2, 0).getUnits().peek();
        List<MoveAction> moves = grassHopper.generateValidMoves(new Coord(2, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 2", 2, moves.size());
        assertEquals("moves should be as expected", str, "Move GrassHopper(WHITE), from: Coord{2, 0}, to: Coord{2, -2}}, Move GrassHopper(WHITE), from: Coord{2, 0}, to: Coord{-4, 0}}");
    }

    @Test
    public void queenShouldGenerateValidMoves() {
        queenCanMove(); // sets up the board with specific units placed down

        GameUnit queen = game.getBoard().get(0, 0).getUnits().peek();
        List<MoveAction> moves = queen.generateValidMoves(new Coord(1, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 2", 2, moves.size());
        assertEquals("moves should be as expected", str, "Move QueenBee(WHITE), from: Coord{1, 0}, to: Coord{1, -1}}, Move QueenBee(WHITE), from: Coord{1, 0}, to: Coord{0, 1}}");
    }

    @Test
    public void antShouldGenerateValidMoves() {
        setupGame();
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit ant = game.getBoard().get(0, 1).getUnits().peek();
        List<MoveAction> moves = ant.generateValidMoves(new Coord(0, 1));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of X", 17, moves.size());
        assertEquals("moves should be as expected", str, "Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{1, 1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{2, 1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{3, 0}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{3, -1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{3, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{2, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{1, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{0, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{0, -1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-1, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-2, -2}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-3, -1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-4, 0}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-4, 1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-3, 1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-2, 1}}, Move SoldierAnt(WHITE), from: Coord{0, 1}, to: Coord{-1, 1}}");
    }
    @Test
    public void spiderShouldGenerateValidMoves() {
        setupGame();
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit spider = game.getBoard().get(1, -1).getUnits().peek();
        List<MoveAction> moves = spider.generateValidMoves(new Coord(1, -1));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        System.out.println(str);
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of X", 2, moves.size());
        assertEquals("moves should be as expected", "Move Spider(WHITE), from: Coord{1, -1}, to: Coord{3, -1}}, Move Spider(WHITE), from: Coord{1, -1}, to: Coord{-1, -2}}", str);
    }

    @Test
    public void toStringShouldBeOfValidFormat() {
        String correct = "Beetle(WHITE)";
        assertEquals("Beetle should have correct toString format", new Beetle(Hive.Player.WHITE).toString(), correct);
        correct = "QueenBee(WHITE)";
        assertEquals("QueenBee should have correct toString format", new QueenBee(Player.WHITE).toString(), correct);
        correct = "SoldierAnt(WHITE)";
        assertEquals("SoldierAnt should have correct toString format", new SoldierAnt(Player.WHITE).toString(), correct);
        correct = "Spider(WHITE)";
        assertEquals("Spider should have correct toString format", new Spider(Hive.Player.WHITE).toString(), correct);
        correct = "GrassHopper(WHITE)";
        assertEquals("GrassHopper should have correct toString format", new GrassHopper(Player.WHITE).toString(), correct);

        correct = "Beetle(BLACK)";
        assertEquals("Beetle should have correct toString format", new Beetle(Player.BLACK).toString(), correct);
        correct = "QueenBee(BLACK)";
        assertEquals("QueenBee should have correct toString format", new QueenBee(Hive.Player.BLACK).toString(), correct);
        correct = "SoldierAnt(BLACK)";
        assertEquals("SoldierAnt should have correct toString format", new SoldierAnt(Player.BLACK).toString(), correct);
        correct = "Spider(BLACK)";
        assertEquals("Spider should have correct toString format", new Spider(Player.BLACK).toString(), correct);
        correct = "GrassHopper(BLACK)";
        assertEquals("GrassHopper should have correct toString format", new GrassHopper(Hive.Player.BLACK).toString(), correct);
    }

    @Test
    public void canMoveFromAtoBTest() {
        GameBoard gb = HiveGameFactory.getNew().getBoard();
        GameUnit a = new Beetle(Hive.Player.BLACK);
        gb.get(0, 0).acceptUnit(a);
        assertThat("Unit a can not move one place", a.canMoveFromAToB(0, 0, 0, 1), is(false));
        gb.get(1, 0).acceptUnit(new Beetle(Hive.Player.WHITE));
        assertThat("Unit a can move one place", a.canMoveFromAToB(0, 0, 0, 1), is(true));
        gb.get(-1, +1).acceptUnit(new Beetle(Player.WHITE));
        assertThat("Unit a can not move one place", a.canMoveFromAToB(0, 0, 0, 1), is(false));
    }

}
