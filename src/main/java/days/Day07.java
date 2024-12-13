package days;

import java.util.*;

public class Day07 implements Day {


    @Override
    public int part1(List<String> strings) {
        Map<Integer, List<Integer>> infoMap = getInfoMap(strings);
        int count = 0;
        for (Map.Entry<Integer, List<Integer>> entry : infoMap.entrySet()) {
            if (verifyValues(entry.getKey(), entry.getValue())) {
                count += entry.getKey();
            }
        }
        return count;
    }

    @Override
    public int part2(List<String> strings) {
        return 0;
    }

    private Map<Integer, List<Integer>> getInfoMap(List<String> strings) {
        Map<Integer, List<Integer>> infoMap = new HashMap<>();
        for (String s : strings) {
            String[] split = s.split(":");
            String[] split2 = split[1].split(" ");
            List<Integer> list = new ArrayList<>();

            for (String number : split2) {
                if (!number.trim().isEmpty()) {
                    list.add(Integer.parseInt(number.trim()));
                }
            }

            infoMap.put(Integer.parseInt(split[0].trim()), list);
        }
        return infoMap;
    }


    private boolean verifyValues(Integer target, List<Integer> values) {
        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        for (int value : values) {
            Set<Integer> newSums = new HashSet<>();
            for (int sum : sums) {
                newSums.add(sum + value);
                newSums.add(sum * value);
            }
            sums = newSums;
        }
        return sums.contains(target);
    }
}