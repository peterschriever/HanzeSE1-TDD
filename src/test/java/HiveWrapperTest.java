import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveWrapper;
import nl.hanze.hive.Player.CluelessAI;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Game.*;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Units.Beetle;
import nl.hanze.hive.Units.QueenBee;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class HiveWrapperTest {
    private HiveWrapper game;

    private Actor playerWhite;
    private Actor playerBlack;

    @Before
    public void setupGame() {
        playerWhite = new CluelessAI(Hive.Player.WHITE);
        playerBlack = new CluelessAI(Player.BLACK);
        this.game = new HiveWrapper(playerWhite, playerBlack);
    }

    @Test
    public void gameShouldUsePlayerAI() {
        playerWhite = new CluelessAI(Hive.Player.WHITE);
        playerBlack = new CluelessAI(Hive.Player.BLACK);
        HiveWrapper game = new HiveWrapper(playerWhite, playerBlack);
        assertThat("Player AI for white should be playerWhite", game.getPlayerAI(Player.WHITE), is(playerWhite));
        assertThat("Player AI for black should be playerBlack", game.getPlayerAI(Hive.Player.BLACK), is(playerBlack));
    }

    // Requirement 3a
    @Test
    public void gameShouldKeepTrackOfTurn() {
        assertThat("White should start", game.getTurn(), is(Hive.Player.WHITE));
        game.playTurn();
        assertThat("Black should go second", game.getTurn(), is(Hive.Player.BLACK));
        game.playTurn();
        assertThat("White should start", game.getTurn(), is(Player.WHITE));
    }

    @Test
    public void gameShouldHaveABoard() {
        assertThat("The game has a board based on the GameBoard class", game.getBoard(), instanceOf(GameBoard.class));
    }

    @Test
    public void gameShouldKeepAPlayLog() {
        assertThat("Game has a PlayLog object", game.getPlayLog(), instanceOf(PlayLog.class));
    }

    @Test
    public void gameShouldPlayATurn() {
        assertThat("White should start", game.getTurn(), is(Hive.Player.WHITE));
        assertThat("playLog should be empty", game.getPlayLog().size(), is(0));
        assertEquals("board should only have root field", game.getBoard().size(), 1);

        game.playTurn();

        assertThat("Black should be next", game.getTurn(), is(Player.BLACK));
        assertThat("playLog should have 1 entry", game.getPlayLog().size(), is(1));
        Field fieldWithUnit = new Field(0,0);
        fieldWithUnit.acceptUnit(new QueenBee(Hive.Player.WHITE)); // Random move
        assertEquals("Board root field should be the same", game.getBoard().get(0,0).getUnits().size(), fieldWithUnit.getUnits().size());
    }

    @Test
    public void testCountOfSurroundingUnits() {
        HiveWrapper g = HiveGameFactory.getShadow();
        g.getBoard().get(0, 0).acceptUnit(new QueenBee(Hive.Player.BLACK));
        g.getBoard().get(0,1).acceptUnit(new Beetle(Hive.Player.BLACK));
        g.getBoard().get(1, 0).acceptUnit(new QueenBee(Hive.Player.WHITE));
        g.getBoard().get(-1,0).acceptUnit(new Beetle(Hive.Player.WHITE));
        assertThat("Surrounding unit count of 0,0 should be 3", g.getCountOfSurroundingUnits(0, 0), is(3));
    }

    @Test
    public void gameShouldDetermineWinners() {
        HiveWrapper g = HiveGameFactory.getShadow();
        g.getBoard().get(0, 0).acceptUnit(new QueenBee(Hive.Player.BLACK));
        g.getBoard().get(0,1).acceptUnit(new Beetle(Player.BLACK));
        assertThat("White is not a winner", g.isWinner(Hive.Player.WHITE), is(false));
        assertThat("Black is not a winner", g.isWinner(Hive.Player.BLACK), is(false));
        assertThat("It's not a draw", g.isDraw(), is(false));
        int[][] locations = {{0, -1}, {0, 1}, {1, -1}, {1, 0}, {-1, 0}, {-1, 1}};
        for(int[] loc : locations) {
            g.getBoard().get(loc[0], loc[1]).acceptUnit(new Beetle(Hive.Player.BLACK));
        }
        assertThat("White is a winner", g.isWinner(Hive.Player.WHITE), is(true));
        assertThat("Black is not a winner", g.isWinner(Hive.Player.BLACK), is(false));
        assertThat("It's not a draw", g.isDraw(), is(false));
        int start_q = 5;
        int start_r = 5;
        g.getBoard().get(0, 0).acceptUnit(new QueenBee(Hive.Player.WHITE));
        for(int[] loc : locations) {
            g.getBoard().get(start_q + loc[0], start_r + loc[1]).acceptUnit(new Beetle(Hive.Player.BLACK));
        }
        assertThat("Black is a winner", g.isWinner(Hive.Player.BLACK), is(true));
        assertThat("White is a winner", g.isWinner(Hive.Player.WHITE), is(true));
        assertThat("It's a draw", g.isDraw(), is(true));
    }

}
