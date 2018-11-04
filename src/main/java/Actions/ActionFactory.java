package Actions;

import Game.*;
import Player.Player;
import Units.*;

import java.util.*;

public class ActionFactory {

    private ActionFactory() {
    }

    // TODO: Write test!!!
    public static List<Action> generateValidActions(Player player) {
        // Get board, look at fields
        List<Action> actions = new LinkedList<>();
        actions.addAll(ActionFactory.getSpawnActions(player));
        actions.addAll(ActionFactory.getMoveActions(player));
        return actions;
    }

    public static List<Action> getMoveActions(Player player) {
        return new LinkedList<>();
    }

    public static List<Action> getSpawnActions(Player player) {
        HiveGame game = HiveGameFactory.getInstance();
        ArrayList<Field> unit_fields = game.getBoard().getFieldsWithUnits();
        List<Action> actions = new LinkedList<>();
        boolean are_we_represented = false;
        for(Field f: unit_fields) {
            if(f.getUnits().peek() != null) {
                if(f.getUnits().peek().getColour() == player.colour) {
                    are_we_represented = true;
                }
            }
        }
        if (unit_fields.isEmpty() || !are_we_represented) {
            int[] coord = new int[2];
            if(unit_fields.isEmpty()) {
                coord[0] = 0;
                coord[1] = 0;
            } else {
                coord[0] = 0;
                coord[1] = -1;
            }
            // Spawn action on 0,0
            actions.add(new SpawnAction(new QueenBee(player.colour), new Coord(coord[0], coord[1])));
            actions.add(new SpawnAction(new Beetle(player.colour), new Coord(coord[0], coord[1])));
            actions.add(new SpawnAction(new GrassHopper(player.colour), new Coord(coord[0], coord[1])));
            actions.add(new SpawnAction(new SoldierAnt(player.colour), new Coord(coord[0], coord[1])));
            actions.add(new SpawnAction(new Spider(player.colour), new Coord(coord[0], coord[1])));
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

    public static ArrayList<Field> getSpawnFields(Player player) {
        HiveGame game = HiveGameFactory.getInstance();
        ArrayList<Field> unit_fields = game.getBoard().getFieldsWithUnits();
        ArrayList<Field> available_fields = new ArrayList<>();
        Hive.Colour us = player.colour;
        Hive.Colour them = (player.colour == Hive.Colour.BLACK) ? Hive.Colour.WHITE : Hive.Colour.BLACK;
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

    private static boolean shouldPlayQueen(Player player) {
        if (player.queenbee + player.ant + player.beetle + player.grasshopper + player.spider <= 11 - 3) {
            if (player.queenbee > 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean canMoveUnits(Player player) {
        return player.queenbee == 0;
    }

}
