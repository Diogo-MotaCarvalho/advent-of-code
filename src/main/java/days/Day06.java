package days;


import java.util.*;

public class Day06 implements Day {
    List<List<Integer>> visited = new ArrayList<>();

    @Override
    public int part1(List<String> strings) {
        List<List<Character>> matrix = getMap(strings);
        List<Integer> startingPosition = startingPosition(matrix);
        List<Integer> direction = new ArrayList<>();
        direction.add(-1);
        direction.add(0);
        int i = startingPosition.getFirst();
        int j = startingPosition.getLast();

        while (true) {
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

    @Override
    public int part2(List<String> strings) {
      List<List<Character>> matrix = getMap(strings);
      List<Integer> startingPosition = startingPosition(matrix);
      List<Integer> direction = new ArrayList<>();
      direction.add(-1);
      direction.add(0);
      int count = 0;

      for (int k = 0; k < matrix.size(); k++) {

          for (int l = 0; l < matrix.getFirst().size(); l++) {

              List<Character> newLine = matrix.get(k);
              newLine.set(l,new Character('#'));
              matrix.set(k,newLine);

              if(isLoop(matrix)){
                  count++;
              }
          }
      }
    return 0;
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

    private String checkDirection(List<List<Integer>> visited) {

        List<Integer> lastVisited = visited.getLast();
        List<Integer> lastLastVisited = visited.get(visited.size() - 2);

        if (lastLastVisited.getFirst() - lastVisited.getFirst() == -1) {
            return "left";
        }
        if (lastLastVisited.getFirst() - lastVisited.getFirst() == 1) {
            return "right";
        }
        if (lastLastVisited.getLast() - lastVisited.getLast() == -1) {
            return "down";
        }
        if (lastLastVisited.getLast() - lastVisited.getLast() == 1) {
            return "up";
        }
        return null;
    }

    public static boolean isLoop(List<List<Character>> matriz) {
        int n = matriz.size();
        int m = matriz.get(0).size();

        // Encontrar a posição inicial da seta '^'
        int startX = -1, startY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matriz.get(i).get(j) == '^') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }

        // Direções: 0=cima, 1=direita, 2=baixo, 3=esquerda
        int[] dx = {-1, 0, 1, 0}; // movimentação vertical
        int[] dy = {0, 1, 0, -1}; // movimentação horizontal

        // Direção inicial (vamos supor que a pessoa começa indo para cima)
        int dir = 0; // Começa indo para cima (direção 0)

        // Conjunto de posições e direções visitadas
        Set<String> visitadas = new HashSet<>();

        int x = startX, y = startY;

        while (true) {
            // Se a pessoa chegou a um loop
            String estado = x + "," + y + "," + dir;
            if (visitadas.contains(estado)) {
                return true; // Loop infinito detectado
            }

            // Adicionar a posição e direção ao conjunto de visitadas
            visitadas.add(estado);

            // Verificar a próxima posição na direção atual
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // Verificar se a nova posição está dentro dos limites da matriz
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || matriz.get(nx).get(ny) == '#') {
                // Se houver um obstáculo, gira 90 graus para a direita
                dir = (dir + 1) % 4;
            } else {
                // Senão, move para a nova posição
                x = nx;
                y = ny;
            }
        }
    }

}