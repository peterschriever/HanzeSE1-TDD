package nl.hanze.hive.Player;

import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Actions.MoveAction;
import nl.hanze.hive.Actions.SpawnAction;
import nl.hanze.hive.Hive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Human extends Actor {
    public Human(Hive.Player c) {
        super(c);
    }

    @Override
    public Action chooseAction() {
        List<Action> validActions = ActionFactory.generateValidActions(this);
        List<Action> spawnActions = validActions.stream()
            .filter(a -> a instanceof SpawnAction)
            .collect(Collectors.toList());
        List<Action> moveActions = validActions.stream()
            .filter(a -> a instanceof MoveAction)
            .collect(Collectors.toList());

        int i;
        int input;
        if (moveActions.size() > 0 && spawnActions.size() > 0) {
            input = getInputNumber(2);
            if (input == 1) validActions = spawnActions;
            else validActions = moveActions;
        }

        // @TODO: actions by unit type
//        if (validActions.size() > 5) { // 5 is some abritrary number at which to activate unit selection
//            Class[] units = {Beetle.class, GrassHopper.class, QueenBee.class, SoldierAnt.class, Spider.class};
//            for (i = 0; i < units.length; i++) {
//            }
//            input = getInputNumber(units.length);
//            final Class unitType = units[input];
//            validActions = validActions.stream()
//                .filter(a -> a.getUnit().getClass() == unitType)
//                .collect(Collectors.toList());
//        }

        if(validActions.size() == 0) return null;
        i = 0;
        for (Action a : validActions) {
            i++;

        }
        input = getInputNumber(validActions.size());
        return validActions.get(input - 1);
    }

    private int getInputNumber(int max) {
        int result = 0;
        while (result == 0) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine();
                result = Integer.parseInt(s);
            } catch (Exception e) {
                continue;
            }
            if (result > max) {
                result = 0;
                continue;
            }
            if (result == 0) {
                System.exit(100);
            }
        }
        return result;
    }
}
