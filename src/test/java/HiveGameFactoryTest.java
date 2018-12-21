import Game.HiveWrapper;
import Game.HiveGameFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class HiveGameFactoryTest {
    @Test
    public void testInstanceEquality(){
        HiveWrapper first = HiveGameFactory.getInstance();
        HiveWrapper second = HiveGameFactory.getInstance();
        assertSame("Instances should be the same", first, second);
        HiveWrapper third = HiveGameFactory.getNew();
        assertNotSame("Instances should not be equal", first, third);
        HiveWrapper fourth = HiveGameFactory.getNew();
        assertNotSame("Instances should not be equal", third, fourth);
        HiveWrapper fifth = HiveGameFactory.getInstance();
        assertSame("Instances should be equal", fourth, fifth);
        HiveGameFactory.getShadow();
        assertSame("Instances should be equal", fifth, HiveGameFactory.getInstance());

    }
}
