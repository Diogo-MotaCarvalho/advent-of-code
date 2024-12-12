package days;

import java.util.List;

public class Day04 implements Day {
    char[][] matrix;

    int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };

    @Override
    public int part1(List<String> strings) {

        int rows = strings.get(0).length();
        int columns = strings.size();
        matrix = new char[rows][columns];
        int count = 0;
        int lineIndex = 0;
        for (String line : strings) {
            matrix[lineIndex] = line.toCharArray();
            lineIndex++;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int[] direction : directions) {
                    if (xmas(matrix, i, j, direction)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public int part2(List<String> strings) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (mas(matrix, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    boolean xmas(char[][] matrix, int row, int column, int[] direction) {
        String word = "XMAS";
        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < word.length(); i++) {

            int nextRow = row + i * direction[0];
            int nextColumn = column + i * direction[1];

            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || matrix[nextRow][nextColumn] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

   //boolean mas(char[][] matrix, int row, int column) {
   //    return matrix[row][column] == 'A' && matrix[row-1][column-1] == 'M' && matrix[row+1][column+1] == 'S'
   //        && matrix[row-1][column+1] == 'S' && matrix[row+1][column+1] == 'M'
   //        || matrix[row][column] == 'A' && matrix[row-1][column-1] == 'S' && matrix[row+1][column+1] == 'M'
   //        && matrix[row-1][column+1] == 'M' && matrix[row+1][column+1] == 'S';
   //}
    boolean mas(char[][] matrix, int row, int column) {
        return matrix[row][column] == 'A'
            && (matrix[row-1][column-1] == 'M' && matrix[row+1][column+1] == 'S' || matrix[row-1][column-1] == 'S' && matrix[row+1][column+1] == 'M')
            && (matrix[row+1][column-1] == 'S' && matrix[row-1][column+1] == 'M' || matrix[row+1][column-1] == 'M' && matrix[row-1][column+1] == 'S');
    }
}                    //
                     //     [-1][-1]M.S[-1][1]
                     //             .A.
                     //      [1][-1]M.S[1][1]
                     //
                     //1967
                     //
                     //
