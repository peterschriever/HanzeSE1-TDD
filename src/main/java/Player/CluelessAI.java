package Player;

import Actions.Action;
import Actions.ActionFactory;
import Game.Hive;

import java.util.List;
import java.util.Random;

public class CluelessAI extends Player {

    public CluelessAI(Hive.Colour c) {
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
        System.out.println("Player decided to play " + a);
        return a;
    }
}
