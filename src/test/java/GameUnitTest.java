
import Game.Hive;
import Units.GameUnit;
import Units.QueenBee;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

public class GameUnitTest {

    @Test
    public void GameUnitShouldPersistPlayer() {
        Hive.Colour colour = Hive.Colour.BLACK;
        GameUnit qb = new QueenBee(colour);
        assertEquals("QueenBee colour should be black", qb.getColour(), Hive.Colour.BLACK);
    }

}
