package Game;

public class HiveGameFactory {
    private static HiveGame instance;

    public static HiveGame getInstance() {
        if(HiveGameFactory.instance == null) {
            return HiveGameFactory.getNew();
        }
        return HiveGameFactory.instance;
    }

    public static HiveGame getNew() {
        HiveGame game = new HiveGame();
        HiveGameFactory.instance = game;
        return game;
    }

    public static HiveGame getShadow() {
        return new HiveGame();
    }
}
