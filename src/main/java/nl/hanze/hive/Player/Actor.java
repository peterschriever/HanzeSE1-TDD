package nl.hanze.hive.Player;

import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Hive;

public abstract class Actor {
    public int queenbee = 1;
    public int beetle = 2;
    public int spider = 2;
    public int ant = 3;
    public int grasshopper = 3;
    public Hive.Player colour;

    public Actor(Hive.Player c) {
        this.colour = c;
    }
    public abstract Action chooseAction();
}
