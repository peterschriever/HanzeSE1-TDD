import Game.Field;
import Game.GameUnit;
import Game.Hive;
import Units.QueenBee;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FieldTest {

    @Test public void fieldHasQandR() {
        Field field = new Field(0, 0);
        assertEquals("field should have a q value of 0", 0, field.getQ());
        assertEquals("field should have a r value of 0", 0, field.getR());
        field = new Field(5, 5);
        assertEquals("field should have a q value of 5", 5, field.getQ());
        assertEquals("field should have a r value of 5", 5, field.getR());
    }

    @Test public void fieldShouldAcceptFirstUnit() {
        Field field = new Field(0, 0);
        GameUnit unit = new QueenBee(Hive.Player.WHITE);
        field.acceptUnit(unit);
        assertThat(field.getUnits(), hasItem(unit));
    }

}
