package Player;

import Actions.Action;
import Game.Hive;

public abstract class Player {
    public int queenbee = 1;
    public int beetle = 2;
    public int spider = 2;
    public int ant = 3;
    public int grasshopper = 3;
    public Hive.Colour colour;

    public Player(Hive.Colour c) {
        this.colour = c;
    }
    public abstract Action chooseAction();
}
