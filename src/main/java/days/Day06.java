package days;


import java.util.ArrayList;
import java.util.List;

public class Day06 implements Day {


    @Override
    public int part1(List<String> strings) {
        List<List<Character>> matrix = getMap(strings);
        List<List<Integer>> visited = new ArrayList<>();
        List<Integer> startingPosition = startingPosition(matrix);
        List<Integer> direction = new ArrayList<>();
        direction.add(-1);
        direction.add(0);
        for (int i = startingPosition.getFirst(); i < matrix.size(); ) {
            for (int j = startingPosition.getLast(); j < matrix.get(i).size() ; ) {
                //turn 90 degrees right
                if (matrix.get(i).get(j) == '#') {
                    List<Integer> newDirection = turn(visited);
                    direction.set(0, newDirection.getFirst());
                    direction.set(1, newDirection.getLast());
                    i = Integer.sum(visited.getLast().getFirst(), direction.getFirst());
                    j = Integer.sum(visited.getLast().getLast(), direction.getLast());
                } else {//Keeps moving and adds position to visited positions
                    List<Integer> positions = new ArrayList<>();
                    positions.add(i);
                    positions.add(j);
                    visited.add(positions);
                    i = Integer.sum(visited.getLast().getFirst(), direction.getFirst());
                    j = Integer.sum(visited.getLast().getLast(), direction.getLast());
                }
            }
        }
        return 0;
    }

    @Override
    public int part2(List<String> strings) {
        return 0;
    }


    private List<List<Character>> getMap(List<String> strings) {
        List<List<Character>> map = new ArrayList<>();
        for (String string : strings) {
            List<Character> characters = new ArrayList<>();
            char[] chars = string.toCharArray();
            for (char c : chars) {
                characters.add(c);
            }
            map.add(characters);
        }
        return map;
    }

    private List<Integer> startingPosition(List<List<Character>> matrix) {
        List<Integer> startingPosition = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == '^') {
                    startingPosition.add(i);
                    startingPosition.add(j);
                    return startingPosition;
                }
            }
        }
        return startingPosition;
    }

    private List<Integer> turn(List<List<Integer>> visited) {
        List<Integer> turn = new ArrayList<>();

        List<Integer> lastVisited = visited.getLast();
        List<Integer> lastLastVisited = visited.get(visited.size() - 2);

        if (lastLastVisited.getFirst() - lastVisited.getFirst() == -1) {
            turn.add(0);
            turn.add(1);
        }
        if (lastLastVisited.getFirst() - lastVisited.getFirst() == 1) {
            turn.add(0);
            turn.add(-1);
        }
        if (lastLastVisited.getLast() - lastVisited.getLast() == -1) {
            turn.add(1);
            turn.add(0);
        }
        if (lastLastVisited.getLast() - lastVisited.getLast() == 1) {
            turn.add(-1);
            turn.add(0);
        }
        return turn;
    }
}