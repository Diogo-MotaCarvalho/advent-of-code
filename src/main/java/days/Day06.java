package days;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 implements Day {
    List<List<Integer>> visited = new ArrayList<>();

    @Override
    public int part1(List<String> strings) {
        List<List<Character>> matrix = getMap(strings);
        List<Integer> startingPosition = startingPosition(matrix);
        List<Integer> direction = new ArrayList<>();
        direction.add(-1);
        direction.add(0);
        for (int i = startingPosition.getFirst(); ; ) {
            for (int j = startingPosition.getLast(); ; ) {
                //stop conditions
                if (i >= matrix.size() || j >= matrix.get(0).size() || i < 0 || j < 0) {
                    return removeDuplicates(visited).size();
                }
                //turn 90 degrees right
                if (matrix.get(i).get(j) == '#') {
                    direction = turn(visited);
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
    }

    @Override
    public int part2(List<String> strings) {
        List<List<Integer>> visitedNoDuplicates = removeDuplicates(visited);
        int count = 0;
        for (int i = 0; i < visitedNoDuplicates.size(); i++) {
            for (int j = 0; j < visitedNoDuplicates.get(i).size(); j++) {
                if (visitedNoDuplicates.get(i).getFirst() == visitedNoDuplicates.get(j).getFirst()) {
                    for (int k = 0; k < visitedNoDuplicates.get(i).size(); k++) {
                        if (visitedNoDuplicates.get(j).getLast() == visitedNoDuplicates.get(k).getLast()) {
                            for (int l = 0; l < visitedNoDuplicates.get(i).size(); l++) {
                                if (visitedNoDuplicates.get(l).getFirst() == visitedNoDuplicates.get(k).getFirst() && visitedNoDuplicates.get(l).getLast() == visitedNoDuplicates.get(i).getLast()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
    //[0][1] [0][2] [0][3]
    //[1][1] [1][2] [1][3]
    //[2][1] [2][2] [2][3]
    //[3][1] [3][2] [3][3]


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
            turn.add(-1);
        }
        if (lastLastVisited.getFirst() - lastVisited.getFirst() == 1) {
            turn.add(0);
            turn.add(1);
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

    private <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

}