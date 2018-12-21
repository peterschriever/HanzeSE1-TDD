package nl.hanze.hive.Player;

import nl.hanze.hive.Actions.Action;
import nl.hanze.hive.Actions.ActionFactory;
import nl.hanze.hive.Hive;

import java.util.List;
import java.util.Random;

public class CluelessAI extends Actor {

    public CluelessAI(Hive.Player c) {
        super(c);
    }

    @Override
    public Action chooseAction() {
        List<Action> validActions = ActionFactory.generateValidActions(this);

        if(validActions.size() == 0)
            return null;
        Random rand = new Random();
        int randomInt = rand.nextInt(validActions.size());
        Action a = validActions.get(randomInt);
        return a;
    }
}
