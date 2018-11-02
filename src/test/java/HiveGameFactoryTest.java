import Game.HiveGame;
import Game.HiveGameFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class HiveGameFactoryTest {
    @Test
    public void testInstanceEquality(){
        HiveGame first = HiveGameFactory.getInstance();
        HiveGame second = HiveGameFactory.getInstance();
        assertSame("Instances should be the same", first, second);
        HiveGame third = HiveGameFactory.getNew();
        assertNotSame("Instances should not be equal", first, third);
        HiveGame fourth = HiveGameFactory.getNew();
        assertNotSame("Instances should not be equal", third, fourth);
        HiveGame fifth = HiveGameFactory.getInstance();
        assertSame("Instances should be equal", fourth, fifth);
        HiveGameFactory.getShadow();
        assertSame("Instances should be equal", fifth, HiveGameFactory.getInstance());

    }
}
