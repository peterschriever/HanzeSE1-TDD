package Player;

import Actions.Action;
import Actions.ActionFactory;
import Actions.MoveAction;
import Actions.SpawnAction;
import Game.Hive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Human extends Player {
    public Human(Hive.Colour c) {
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
            System.out.println("Select an action category! Enter 0 to exit.");
            System.out.println("1 - Spawn a unit");
            System.out.println("2 - Move a unit");
            input = getInputNumber(2);
            if (input == 1) validActions = spawnActions;
            else validActions = moveActions;
        }

        // @TODO: actions by unit type
//        if (validActions.size() > 5) { // 5 is some abritrary number at which to activate unit selection
//            System.out.println("Choose a unit type! Enter 0 to exit.");
//            Class[] units = {Beetle.class, GrassHopper.class, QueenBee.class, SoldierAnt.class, Spider.class};
//            for (i = 0; i < units.length; i++) {
//                System.out.println(i + 1 + " " + units[i].toString().replaceAll("class Units.", ""));
//            }
//            input = getInputNumber(units.length);
//            final Class unitType = units[input];
//            validActions = validActions.stream()
//                .filter(a -> a.getUnit().getClass() == unitType)
//                .collect(Collectors.toList());
//        }

        if(validActions.size() == 0) return null;
        i = 0;
        System.out.println("Select an action between 1 and " + validActions.size() + "! Enter 0 to exit.");
        for (Action a : validActions) {
            i++;
            System.out.println(i + " " + a);

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
                System.out.println("Whoops! Try to enter a number!");
                continue;
            }
            if (result > max) {
                result = 0;
                System.out.println("Whoops! That result is higher than the boundary.");
                continue;
            }
            if (result == 0) {
                System.exit(100);
            }
        }
        return result;
    }
}
