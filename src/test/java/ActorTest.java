import nl.hanze.hive.Hive;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Player.CluelessAI;
import nl.hanze.hive.Player.Human;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActorTest {

    // Requirement 1c
    @Test
    public void actorShouldHaveUnits() {
        Actor actor1 = new Human(Hive.Player.WHITE);
        assertEquals("Should have 1 queenbee", 1, actor1.queenbee);
        assertEquals("Should have 2 spiders", 2, actor1.spider);
        assertEquals("Should have 2 beetles", 2, actor1.beetle);
        assertEquals("Should have 3 ants", 3, actor1.ant);
        assertEquals("Should have 3 grasshoppers", 3, actor1.grasshopper);

        Actor actor2 = new CluelessAI(Hive.Player.BLACK);
        assertEquals("Should have 1 queenbee", 1, actor2.queenbee);
        assertEquals("Should have 2 spiders", 2, actor2.spider);
        assertEquals("Should have 2 beetles", 2, actor2.beetle);
        assertEquals("Should have 3 ants", 3, actor2.ant);
        assertEquals("Should have 3 grasshoppers", 3, actor2.grasshopper);
    }

}
