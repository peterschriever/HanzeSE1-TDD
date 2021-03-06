import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Units.GameUnit;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Units.GrassHopper;
import nl.hanze.hive.Units.QueenBee;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void fieldHasQandR() {
        Field field = new Field(0, 0);
        assertEquals("field should have a q value of 0", 0, field.getQ());
        assertEquals("field should have a r value of 0", 0, field.getR());
        field = new Field(5, 5);
        assertEquals("field should have a q value of 5", 5, field.getQ());
        assertEquals("field should have a r value of 5", 5, field.getR());
    }

    @Test
    public void fieldShouldAcceptFirstUnit() {
        Field field = new Field(0, 0);
        GameUnit unit = new QueenBee(Hive.Player.WHITE);
        field.acceptUnit(unit);
        assertThat(field.getUnits(), hasItem(unit));
    }

    @Test
    public void fieldEqualsShouldTestProps() {
        Field fieldA = new Field(0, 0);
        Field fieldB = new Field(0, 0);
        assertEquals("Fields should be equals", fieldA, fieldB);

        fieldB = new Field(0, 1);
        assertNotEquals("Fields should not be equals", fieldA, fieldB);

        fieldB = new Field(0, 0);
        fieldB.acceptUnit(new QueenBee(Hive.Player.WHITE));
        assertNotEquals("Fields should not be equals", fieldA, fieldB);

        GameUnit grassHopper = new GrassHopper(Hive.Player.WHITE);
        fieldA.acceptUnit(grassHopper);
        assertNotEquals("Fields should not be equals", fieldA, fieldB);

        fieldB = new Field(0, 0);
        fieldB.acceptUnit(grassHopper);
        assertEquals("Fields should be equals", fieldA, fieldB);
    }

}
