package nl.hanze.hive.Actions;

import nl.hanze.hive.Game.Coord;
import nl.hanze.hive.Game.Field;
import nl.hanze.hive.Game.GameBoard;
import nl.hanze.hive.Game.HiveGameFactory;
import nl.hanze.hive.Hive;
import nl.hanze.hive.HiveWrapper;
import nl.hanze.hive.Player.Actor;
import nl.hanze.hive.Units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ActionFactory {

    private ActionFactory() {
    }

    public static List<Action> generateValidActions(Actor player) {
        // Get board, look at fields
        List<Action> actions = new LinkedList<>();
        actions.addAll(ActionFactory.getSpawnActions(player));
        if(canMoveUnits(player)) {
            actions.addAll(ActionFactory.getMoveActions(player));
        }
        return actions;
    }

    public static List<Action> getMoveActions(Actor player) {
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        ArrayList<Field> fieldsWithUnits = gb.getFieldsWithUnits();
        List<Action> moveActions = new LinkedList<>();
        List<MoveAction> unitsMoves;
        for (int i = 0; i < fieldsWithUnits.size(); i++) {
            for (int j = 0; j < fieldsWithUnits.get(i).getUnits().size(); j++) {
                GameUnit unit = fieldsWithUnits.get(i).getUnits().get(j);
                if(unit.getColour() == player.colour) {
                    unitsMoves = unit.generateValidMoves(new Coord(fieldsWithUnits.get(i)));
                    if (unitsMoves != null) moveActions.addAll(unitsMoves);
                }
            }
        }

        return moveActions;
    }

//    public static List<Action> getSpawnActions(Actor player) {
//        HiveWrapper game = HiveGameFactory.getInstance();
//        List<Action> actions = new LinkedList<>();
//        Set<GameUnit> units = new HashSet<GameUnit>() {{
//            add(new Beetle(player.colour));
//            add(new GrassHopper(player.colour));
//            add(new QueenBee(player.colour));
//            add(new SoldierAnt(player.colour));
//            add(new Spider(player.colour));
//        }};
//
//        if (!player.hasSpawnablesLeft()) return actions;
//        List<Field> unitFields = game.getBoard().getFieldsWithUnits();
//        if (unitFields.isEmpty()) {
//
//        }
//
//    }

    public static List<Action> getSpawnActions(Actor player) {
        HiveWrapper game = HiveGameFactory.getInstance();
        ArrayList<Field> unit_fields = game.getBoard().getFieldsWithUnits();
        List<Action> actions = new LinkedList<>();
        boolean are_we_represented = false; // units of current player exist on board?
        for(Field f: unit_fields) {
            if(f.getUnits().peek().getColour().equals(player.colour)) {
                are_we_represented = true;
            }
        }
        if (unit_fields.isEmpty() || !are_we_represented) {
            HashMap<Coord, Field> fields = new HashMap<>();
            if(unit_fields.isEmpty()) {
                fields.put(new Coord(0,0), new Field(0, 0));
            } else {
                fields = game.getBoard().getNeighboursForField(unit_fields.get(0));
            }
            for(Field f: fields.values()) {
                actions.add(new SpawnAction(new QueenBee(player.colour), new Coord(f.getQ(), f.getR())));
                actions.add(new SpawnAction(new Beetle(player.colour), new Coord(f.getQ(), f.getR())));
                actions.add(new SpawnAction(new GrassHopper(player.colour), new Coord(f.getQ(), f.getR())));
                actions.add(new SpawnAction(new SoldierAnt(player.colour), new Coord(f.getQ(), f.getR())));
                actions.add(new SpawnAction(new Spider(player.colour), new Coord(f.getQ(), f.getR())));
            }
        } else {
            ArrayList<Field> available_fields = ActionFactory.getSpawnFields(player);
            if (ActionFactory.shouldPlayQueen(player)) {
                for (Field f : available_fields) {
                    actions.add(new SpawnAction(new QueenBee(player.colour), new Coord(f.getQ(), f.getR())));
                }
            } else {
                for (Field f : available_fields) {
                    if (player.queenbee > 0) {
                        actions.add(new SpawnAction(new QueenBee(player.colour), new Coord(f.getQ(), f.getR())));
                    }
                    if (player.ant > 0) {
                        actions.add(new SpawnAction(new SoldierAnt(player.colour), new Coord(f.getQ(), f.getR())));
                    }
                    if (player.beetle > 0) {
                        actions.add(new SpawnAction(new Beetle(player.colour), new Coord(f.getQ(), f.getR())));
                    }
                    if (player.spider > 0) {
                        actions.add(new SpawnAction(new Spider(player.colour), new Coord(f.getQ(), f.getR())));
                    }
                    if (player.grasshopper > 0) {
                        actions.add(new SpawnAction(new GrassHopper(player.colour), new Coord(f.getQ(), f.getR())));
                    }
                }
            }
        }
        return actions;
    }

    public static ArrayList<Field> getSpawnFields(Actor player) {
        HiveWrapper game = HiveGameFactory.getInstance();
        ArrayList<Field> unit_fields = game.getBoard().getFieldsWithUnits();
        ArrayList<Field> available_fields = new ArrayList<>();
        Hive.Player us = player.colour;
        Hive.Player them = (player.colour == Hive.Player.BLACK) ? Hive.Player.WHITE : Hive.Player.BLACK;
        for (Field f : unit_fields) {
            if (f.getUnits().peek().getColour() != us) continue; // cannot place next to enemy

            HashMap<Coord, Field> neighbours = game.getBoard().getNeighboursForField(f.getQ(), f.getR());
            for (Field n : neighbours.values()) {
                if (n.getUnits().size() > 0) continue;
                boolean can_spawn = true;
                HashMap<Coord, Field> neighbours_of_neighbours = game.getBoard().getNeighboursForField(n.getQ(), n.getR());
                for (Field n_of_n : neighbours_of_neighbours.values()) {
                    if (n_of_n.getUnits().size() > 0) {
                        if (n_of_n.getUnits().peek().getColour() == them) {
                            can_spawn = false;
                        }
                    }
                }
                if (can_spawn) {
                    available_fields.add(n);
                }
            }
        }
        return available_fields;
    }

    private static boolean shouldPlayQueen(Actor player) {
        if (player.queenbee + player.ant + player.beetle + player.grasshopper + player.spider <= 11 - 3) {
            return player.queenbee > 0;
        }
        return false;
    }

    private static boolean canMoveUnits(Actor player) {
        return player.queenbee == 0;
    }

}
