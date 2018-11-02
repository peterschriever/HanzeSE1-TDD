package Actions;

import Game.Hive;
import Game.Pair;
import Units.QueenBee;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {

    private ActionFactory() {
    }

    public static List<Action> generateValidActions(Hive.Colour colour) {
        List<Action> actions = new ArrayList<>();
        actions.add(new SpawnAction(new QueenBee(colour), new Pair<>(0, 0)));
        return actions;
    }

}
