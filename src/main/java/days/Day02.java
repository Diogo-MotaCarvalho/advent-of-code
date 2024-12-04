package days;

import java.util.*;

public class Day02 implements Day {


    @Override
    public int part1(List<String> strings) {

        Set<List<Integer>> reports = populate(strings);

        int safeCount = 0;

        for (List<Integer> row : reports) {
            int dif;
            boolean flag = true;
            boolean flag2;
            boolean decreasing;

            decreasing = row.get(0) < row.get(1);

            for(int i = 0; i < row.size()-1; i++) {

                flag2 = row.get(i) < row.get(i+1);

                dif= Math.abs(row.get(i) - row.get(i+1));

                if (dif > 3 || decreasing!=flag2 || dif==0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                safeCount++;
            }
        }

        return safeCount;
    }

    @Override
    public int part2(List<String> strings) {
        Set<List<Integer>> reports = populate(strings);

        int safeCount = 0;

        for (List<Integer> row : reports) {
            int dif;
            boolean flag = true;
            boolean flag2;
            boolean decreasing;
            int count=0;

            decreasing = row.get(0) < row.get(1);

            for(int i = 0; i < row.size()-1; i++) {


                flag2 = row.get(i) < row.get(i+1);

                dif= Math.abs(row.get(i) - row.get(i+1));

                if (dif > 3 || decreasing!=flag2 || dif==0) {
                    if(count==1){
                        count++;
                        break;
                    }
                    flag = false;
                    count++;
                }
            }
            if (flag || count <=1) {
                safeCount++;
            }
        }

        return safeCount;
    }

    private Set<List<Integer>> populate(List<String> strings) {
        Set<List<Integer>> reports = new HashSet<>();
        for (String s : strings) {
            List<Integer> row = new ArrayList<>();
            String [] split = s.split(" ");
            for(String str : split) {
                row.add(Integer.parseInt(str));
            }
            reports.add(row);
        }
        return reports;
    }

}
