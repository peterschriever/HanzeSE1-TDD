import Game.Field;
import Game.Hive;
import Game.HiveWrapper;
import Game.HiveGameFactory;
import Player.CluelessAI;
import Player.Human;
import Player.Actor;
import Units.GameUnit;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class App {
    public final static Actor white = new Human(Hive.Player.WHITE);
    public final static Actor black = new CluelessAI(Hive.Player.BLACK);
    private final static HashMap<Hive.Player, String> colours = new HashMap<>();
    private final static String colour_black = "\u001B[31m";
    private final static String colour_white = "\u001B[37m";
    private final static String colour_reset = "\u001B[0m";
//    private static String colour_black = "";
//    private static String colour_white = "";
//    private static String colour_reset = "";

    private static void play() {
        HiveWrapper game = HiveGameFactory.getInstance();
        game.setPlayerAI(Hive.Player.WHITE, white);
        game.setPlayerAI(Hive.Player.BLACK, black);

        while (true) {
            boolean draw = game.isDraw();
            boolean black_wins = game.isWinner(Hive.Player.BLACK);
            boolean white_wins = game.isWinner(Hive.Player.WHITE);
            if (draw | black_wins | white_wins) {
                if (draw) {
                    System.out.println("Game ended in a draw!");
                } else if (black_wins) {
                    System.out.println("Black won the game!");
                } else if (white_wins) { // Readability
                    System.out.println("White won the game!");
                }
                break;
            }
            game.playTurn();
            System.out.println("___");
            displayBoard(game);
            System.out.println("___");
            try { // Try to sleep, make AI turns not instant
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Couldn't wait for our turn to end!");
                e.printStackTrace();
            }
        }
    }

    public static void displayBoard(HiveWrapper game) {
        App.colours.put(Hive.Player.BLACK, colour_black);
        App.colours.put(Hive.Player.WHITE, colour_white);
        ArrayList<Field> fieldsWithUnits = game.getBoard().getFieldsWithUnits();
        // Get board edges
        int mostLeft = 99999999;
        int mostRight = -99999999;
        int mostNorth = 9999999;
        int mostSouth = -99999999;
        for (Field f : fieldsWithUnits) {
            if (f.getQ() < mostLeft)
                mostLeft = f.getQ();
            if (f.getQ() > mostRight)
                mostRight = f.getQ();
            if (f.getR() < mostNorth)
                mostNorth = f.getR();
            if (f.getR() > mostSouth)
                mostSouth = f.getR();
        }

        String output = "";
        int r = mostNorth;
        while (r <= mostSouth) {
            StringBuilder tabs = new StringBuilder();
            for (int x = 0; x < r - mostNorth; x++) {
                tabs.append("  ");
            }
            String output_row = tabs.toString();
            int q = mostLeft;
            while (q <= mostRight) {
                Field f = game.getBoard().get(q, r);

                if (!f.getUnits().isEmpty()) {
                    GameUnit u = f.getUnits().peek();
                    output_row = output_row + " " + App.colours.get(u.getColour()) + String.valueOf(u.getCharacter()) + colour_reset + " ";
                } else {
                    output_row = output_row + " . ";
                }
                q++;
            }
            output_row = output_row + "\n";
            output = output + output_row;
            r++;
        }

        System.out.println(output);
    }

    String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        // Do something with args to assign players maybe
        App.play();
    }
}
