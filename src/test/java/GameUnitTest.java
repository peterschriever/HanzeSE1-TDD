
import Actions.Action;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.*;
import Player.CluelessAI;
import Units.*;
import org.junit.Before;
import org.junit.Test;
import Game.Hive.Colour;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameUnitTest {
    private HiveGame game;

    private void setupUnit(GameUnit unit, Coord coord) {
        Action spawnUnit = new SpawnAction(unit, coord);
        game.applyAction(spawnUnit);
    }

    private void queenCanMove() {
        setupUnit(new QueenBee(Colour.WHITE), new Coord(0, 0));
        setupUnit(new QueenBee(Colour.BLACK), new Coord(-1, 0));
    }

    private void allUnitsExceptQueenCanMove() {
        setupUnit(new QueenBee(Colour.WHITE), new Coord(0, 0));
        setupUnit(new Beetle(Colour.WHITE), new Coord(1, 0));
        setupUnit(new SoldierAnt(Colour.WHITE), new Coord(0, 1));
        setupUnit(new Spider(Colour.WHITE), new Coord(1, -1));
        setupUnit(new SoldierAnt(Colour.WHITE), new Coord(2, -1));
        setupUnit(new GrassHopper(Colour.WHITE), new Coord(2, 0));

        setupUnit(new QueenBee(Colour.BLACK), new Coord(-1, 0));
        setupUnit(new Beetle(Colour.BLACK), new Coord(-2, 0));
        setupUnit(new GrassHopper(Colour.BLACK), new Coord(-3, 0));
        setupUnit(new SoldierAnt(Colour.BLACK), new Coord(-2, -1));
        setupUnit(new Spider(Colour.BLACK), new Coord(-1, -1));
    }

    @Before
    public void setupGame() {
        this.game = HiveGameFactory.getInstance();
        game.setPlayerAI(Colour.WHITE, new CluelessAI(Colour.WHITE));
        game.setPlayerAI(Colour.BLACK, new CluelessAI(Colour.BLACK));
    }

    @Test
    public void gameUnitShouldPersistPlayer() {
        Hive.Colour colour = Hive.Colour.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Hive.Colour.BLACK);
    }

    @Test
    public void beetleShouldGenerateValidMoves() {
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit beetle = game.getBoard().get(1, 0).getUnits().peek();
        List<MoveAction> moves = beetle.generateValidMoves(new Coord(1, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 5", 5, moves.size());
        assertEquals("moves should be as expected", str, "action{Beetle(WHITE), Coord{1, 0}, Coord{2, -1}}, action{Beetle(WHITE), Coord{1, 0}, Coord{1, -1}}, action{Beetle(WHITE), Coord{1, 0}, Coord{2, 0}}, action{Beetle(WHITE), Coord{1, 0}, Coord{0, 0}}, action{Beetle(WHITE), Coord{1, 0}, Coord{0, 1}}");
    }

    @Test
    public void grassHopperShouldGenerateValidMoves() {
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit grassHopper = game.getBoard().get(2, 0).getUnits().peek();
        List<MoveAction> moves = grassHopper.generateValidMoves(new Coord(2, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 1", 1, moves.size());
        assertEquals("moves should be as expected", str, "TODO:replaceMe");
    }

    @Test
    public void queenShouldGenerateValidMoves() {
        queenCanMove(); // sets up the board with specific units placed down

        GameUnit queen = game.getBoard().get(0, 0).getUnits().peek();
        List<MoveAction> moves = queen.generateValidMoves(new Coord(1, 0));
        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
        assertNotNull("moves should not be null", moves);
        assertEquals("moves should be size of 2", 2, moves.size());
        assertEquals("moves should be as expected", str, "action{QueenBee(WHITE), Coord{1, 0}, Coord{1, -1}}, action{QueenBee(WHITE), Coord{1, 0}, Coord{0, 1}}");
    }

    @Test
    public void antShouldGenerateValidMoves() {
        setupGame();
        allUnitsExceptQueenCanMove(); // sets up the board with specific units placed down

        GameUnit ant = game.getBoard().get(0, 1).getUnits().peek();
        App.displayBoard(HiveGameFactory.getInstance());
        // TODO: FIX BROKEN SHIT 
//        List<MoveAction> moves = ant.generateValidMoves(new Coord(0, 1));
//        String str = moves.stream().map(Object::toString).collect(Collectors.joining(", "));
//        System.out.println(moves.size());
//        System.out.println(str);
//        assertNotNull("moves should not be null", moves);
//        assertEquals("moves should be size of X", 5, moves.size());
//        assertEquals("moves should be as expected", str, "replaceString");
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

    @Test
    public void canMoveFromAtoBTest() {
        GameBoard gb = HiveGameFactory.getNew().getBoard();
        GameUnit a = new Beetle(Colour.BLACK);
        gb.get(0, 0).acceptUnit(a);
        assertThat("Unit a can move one place", a.canMoveFromAToB(0, 0, 0, 1), is(true));
        gb.get(1, 0).acceptUnit(new Beetle(Colour.WHITE));
        assertThat("Unit a can move one place", a.canMoveFromAToB(0, 0, 0, 1), is(true));
        gb.get(-1, +1).acceptUnit(new Beetle(Colour.WHITE));
        assertThat("Unit a can not move one place", a.canMoveFromAToB(0, 0, 0, 1), is(false));
    }

}
