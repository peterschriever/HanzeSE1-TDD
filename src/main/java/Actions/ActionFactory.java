package Actions;

import Game.Hive;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private ActionFactory() {}

    public static List<Action> generateValidActions(Hive.Player player) {
        List<Action> actions = new ArrayList<>();
        actions.add(new SpawnAction(player));
        return actions;
    }

}
