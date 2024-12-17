package days;

import java.util.*;

public class Day08 implements Day {


    @Override
    public int part1(List<String> strings) {
        char[][] data = getData(strings);
        int count = 0;
        Set<List<Integer>> antiNodes = new HashSet<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] != '.') {
                    System.out.println(data[i][j]);
                    int[] position = new int[]{i, j};
                    antiNodes.addAll(getInLine(position,data));
                }
            }
        }
        return antiNodes.size();
    }

    @Override
    public int part2(List<String> strings) {
        return 0;
    }

    private char[][] getData(List<String> strings) {
        char[][] data = new char[strings.size()][strings.getFirst().length()];

        for (int i = 0; i < strings.size(); i++) {
            data[i] = strings.get(i).toCharArray();
        }

        return data;
    }

    //[0, 6]x[0, 11]x[1, 3]x[2, 4]x[2, 10]x[3, 2]x[4, 9]x[5, 1]x[5, 6]x[6, 3]x[7, 0]x[7, 7]x[10, 10]x[11, 10]x
    //Get all the points that form a line with point
    private Set<List<Integer>> getInLine(int[] point, char[][] matrix) {
        int[] distance;
        Set<List<Integer>> antiNodeSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == point[0] || j == point[1]) {
                    continue;
                }
                if (matrix[i][j] == matrix[point[0]][point[1]]) {
                    distance = new int[]{
                            i - point[0],
                            j - point[1]
                    };
                    if (i + distance[0] < matrix.length && j + distance[1] < matrix[0].length && i + distance[0] >= 0 && j + distance[1]>= 0) {
                        antiNodeSet.add(Arrays.asList(i + distance[0], j + distance[1]));
                    }
                }
            }
        }
        return antiNodeSet;
    }
}

