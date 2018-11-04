package Player;

import Actions.Action;
import Actions.ActionFactory;
import Game.Hive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Human extends Player {
    public Human(Hive.Colour c) {
        super(c);
    }

    @Override
    public Action chooseAction() {
        List<Action> validActions = ActionFactory.generateValidActions(this);
        int i = 0;
        System.out.println("Select an action between 1 and " + validActions.size() + "! Enter 0 to exit.");
        for(Action a: validActions) {
            i++;
            System.out.println(i + " " + a);

        }
        int input = getInputNumber(validActions.size());
        return validActions.get(input-1);
    }
    private int getInputNumber(int max) {

        int result = 0;
        while(result == 0) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String s = br.readLine();
                result = Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println("Whoops! Try to enter a number!");
                continue;
            }
            if(result > max) {
                result = 0;
                System.out.println("Whoops! That result is higher than the boundary.");
                continue;
            }
            if(result == 0) {
                System.exit(100);
            }
        }
        return result;
    }
}
