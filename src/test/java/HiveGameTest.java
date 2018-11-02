import AI.CluelessAI;
import AI.PlayerAI;
import Game.*;
import Game.Hive.Colour;
import Units.QueenBee;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class HiveGameTest {
    private HiveGame game;

    @Before
    public void setupGame() {
        PlayerAI playerWhite = new CluelessAI();
        PlayerAI playerBlack = new CluelessAI();
        this.game = new HiveGame(playerWhite, playerBlack);
    }

    @Test
    public void gameShouldUsePlayerAI() {
        PlayerAI playerWhite = new CluelessAI();
        PlayerAI playerBlack = new CluelessAI();
        HiveGame game = new HiveGame(playerWhite, playerBlack);
        assertThat("Colour AI for white should be playerWhite", game.getPlayerAI(Colour.WHITE), is(playerWhite));
        assertThat("Colour AI for black should be playerBlack", game.getPlayerAI(Hive.Colour.BLACK), is(playerBlack));
    }

    @Test
    public void gameShouldKeepTrackOfTurn() {
        assertThat("White should start", game.getTurn(), is(Hive.Colour.WHITE));
        game.playTurn();
        assertThat("Black should go second", game.getTurn(), is(Colour.BLACK));
        game.playTurn();
        assertThat("White should start", game.getTurn(), is(Colour.WHITE));
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
        assertThat("White should start", game.getTurn(), is(Hive.Colour.WHITE));
        assertThat("playLog should be empty", game.getPlayLog().size(), is(0));
        assertEquals("board should only have root field", game.getBoard().size(), 1);

        game.playTurn();

        assertThat("Black should be next", game.getTurn(), is(Hive.Colour.BLACK));
        assertThat("playLog should have 1 entry", game.getPlayLog().size(), is(1));
        Field fieldWithUnit = new Field(0, 0);
        fieldWithUnit.acceptUnit(new QueenBee(Colour.WHITE));
        assertEquals("Board root field should be the same", game.getBoard().get(0,0), fieldWithUnit);
    }

}
