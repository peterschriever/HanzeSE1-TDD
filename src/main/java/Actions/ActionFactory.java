package Actions;

import Game.*;
import Player.Player;
import Units.*;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {

    private ActionFactory() {
    }

    // TODO: Write test!!!
    public static List<Action> generateValidActions(Player player) {
        Hive.Colour c = player.colour;
        List<Action> actions = new ArrayList<>();
        // Get board, look at fields
        HiveGame game = HiveGameFactory.getInstance();
        ArrayList<Field> unit_fields = game.getBoard().getFieldsWithUnits();
        if(unit_fields.isEmpty()) {
            // Spawn action on 0,0
            actions.add(new SpawnAction(new QueenBee(player.colour), new Pair<>(0,0)));
            actions.add(new SpawnAction(new Beetle(player.colour), new Pair<>(0,0)));
            actions.add(new SpawnAction(new GrassHopper(player.colour), new Pair<>(0,0)));
            actions.add(new SpawnAction(new SoldierAnt(player.colour), new Pair<>(0,0)));
            actions.add(new SpawnAction(new Spider(player.colour), new Pair<>(0,0)));
        }
        return actions;
    }

}
