package Units;

import Actions.MoveAction;
import Game.*;

import java.util.ArrayList;
import java.util.List;

public class Spider extends GameUnit {
    private Hive.Colour colour;

    public Spider(Hive.Colour colour) {
        this.colour = colour;
    }

    public char getCharacter() { return 'S'; }

    @Override
    public Hive.Colour getColour() {
        return this.colour;
    }

    @Override
    public List<MoveAction> generateValidMoves(Coord fromCoord) {
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        if (!this.canFloat(gb)) return null;

        ArrayList<MoveAction> moves = new ArrayList<>();

        Field fromField = gb.get(fromCoord.q, fromCoord.r);
        ArrayList<Field> starting_points = new ArrayList<>();
        for(Field test: gb.getNeighboursForField(fromField).values()) {
            if(hasNeighbouringUnits(test, fromField)) {
                if(test.getUnits().isEmpty())
                    if(canMoveFromAToB(fromField.getQ(), fromField.getR(), test.getQ(), test.getR()))
                    starting_points.add(test);
            }
        }
        System.out.println(starting_points.size());
        for(Field start_point: starting_points) {
            List<Field> path = new ArrayList<>();
            ArrayList<Field> visited = new ArrayList<>();
            path.add(start_point);
            MoveAction move = checkNeighboursForValidMoves(fromField, visited, path, fromCoord);
            if(move != null) {
                moves.add(move);
            }
        }
        return moves;
    }

    private MoveAction checkNeighboursForValidMoves(Field fromField, ArrayList<Field> visited, List<Field> path, Coord original) {
        // Find neighbouring units
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        if(visited.contains(fromField)) {
            return null;
        }
        visited.add(fromField);
        for(Field neighbour: gb.getNeighboursForField(fromField).values()) {
            if(!neighbour.getUnits().isEmpty()) {
                // Neighbouring unit contains neighbouring empty path field
                if(findSharedNeighbours(neighbour, fromField).contains(path.get(path.size()-1))) {
                    List<Field> sharedBetweenPathAndNeighbourUnit = findSharedNeighbours(neighbour, path.get(path.size()-1));
                    Field nextField = null;
                    for(Field testShared: sharedBetweenPathAndNeighbourUnit) {
                        if(testShared.getUnits().isEmpty()) {
                            if(path.contains(testShared))
                                continue;
                            if(!canMoveFromAToB(path.get(path.size()-1).getQ(), path.get(path.size()-1).getR(), testShared.getQ(), testShared.getR())) {
                                continue;
                            }
                            path.add(testShared);
                            if(path.size() == 3){
                                return new MoveAction(this, original, new Coord(path.get(path.size()-1).getQ(), path.get(path.size()-1).getR()));
                            }
                        } else {
                            nextField = testShared;
                        }
                    }
                    return checkNeighboursForValidMoves(nextField, visited, path, original);
                }
            }
        }
        return null;
    }

    private List<Field> findSharedNeighbours(Field a, Field b) {
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        List<Field> neighboursA = new ArrayList<>(gb.getNeighboursForField(a).values());
        List<Field> neighboursB = new ArrayList<>(gb.getNeighboursForField(a).values());
        ArrayList<Field> shared = new ArrayList<>();
        for(Field test: neighboursA) {
            if(neighboursB.contains(test)){
                shared.add(test);
            }
        }
        return shared;
    }
    private boolean hasNeighbouringUnits(Field f, Field not) {
        GameBoard gb = HiveGameFactory.getInstance().getBoard();
        for(Field test: gb.getNeighboursForField(f).values()) {
            if(test.equals(not))
                continue;
            if(!test.getUnits().isEmpty())
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Spider(" + colour + ")";
    }
}
