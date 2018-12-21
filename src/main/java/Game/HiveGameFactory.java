package Game;

public class HiveGameFactory {
    private static HiveWrapper instance;

    public static HiveWrapper getInstance() {
        if(HiveGameFactory.instance == null) {
            return HiveGameFactory.getNew();
        }
        return HiveGameFactory.instance;
    }

    public static HiveWrapper getNew() {
        HiveWrapper game = new HiveWrapper();
        HiveGameFactory.instance = game;
        return game;
    }

    public static HiveWrapper getShadow() {
        return new HiveWrapper();
    }
}
