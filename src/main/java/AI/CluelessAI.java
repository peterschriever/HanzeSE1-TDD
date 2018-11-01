package AI;

import Actions.Action;
import Actions.ActionFactory;
import Game.Hive;

import java.util.List;
import java.util.Random;

public class CluelessAI implements PlayerAI {
    @Override
    public Action chooseAction() {
        List<Action> validActions = ActionFactory.generateValidActions(Hive.Player.WHITE);

        Random rand = new Random();
        int randomInt = rand.nextInt(validActions.size());

        return validActions.get(randomInt);
    }
}
