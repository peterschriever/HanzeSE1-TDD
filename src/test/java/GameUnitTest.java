
import Game.Hive.Player;
import Units.GameUnit;
import Units.QueenBee;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

public class GameUnitTest {

    @Test
    public void GameUnitShouldPersistPlayer() {
        Player player = Player.BLACK;
        GameUnit qb = new QueenBee(player);
        assertEquals("QueenBee player should be black", qb.getPlayer(), Player.BLACK);
    }

}
