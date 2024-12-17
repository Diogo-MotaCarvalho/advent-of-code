package days;

import java.util.*;

public class Day07 implements Day {


    @Override
    public int part1(List<String> strings) {
        Map<Long, List<Long>> infoMap = getInfoMap(strings);
        long count =0;
        for (Map.Entry<Long, List<Long>> entry : infoMap.entrySet()) {
            if (verifyValues(entry.getKey(), entry.getValue())) {
                count += entry.getKey();
            }
        }
        System.out.println(count);
        return 0;
    }

    @Override
    public int part2(List<String> strings) {
        Map<Long, List<Long>> infoMap = getInfoMap(strings);
        long count = 0;
        for (Map.Entry<Long, List<Long>> entry : infoMap.entrySet()) {
            if (verifyValuesPart2(entry.getKey(), entry.getValue())) {
                count += entry.getKey();
            }
        }
        System.out.println(count);
        return 0;
    }

    private Map<Long, List<Long>> getInfoMap(List<String> strings) {
        Map<Long, List<Long>> infoMap = new HashMap<>();
        for (String s : strings) {
            String[] split = s.split(":");
            String[] split2 = split[1].split(" ");
            List<Long> list = new ArrayList<>();

            for (String number : split2) {
                if (!number.trim().isEmpty()) {
                    list.add(Long.parseLong(number.trim()));
                }
            }
            infoMap.put(Long.parseLong(split[0].trim()), list);
        }
        return infoMap;
    }


    private boolean verifyValues(Long target, List<Long> values) {
        Set<Long> sums = new HashSet<>();
        sums.add(0L);
        for (long value : values) {
            Set<Long> newSums = new HashSet<>();
            for (long sum : sums) {
                newSums.add(sum + value);
                newSums.add(sum * value);
            }
            sums = newSums;
        }
        return sums.contains(target);
    }
    //190: 10 19

    private boolean verifyValuesPart2(Long target, List<Long> values) {
        Set<Long> sums = new HashSet<>();
        sums.add(0L);

        for (long value : values) {
            Set<Long> newSums = new HashSet<>();
            for (long sum : sums) {
                newSums.add(sum + value);
                newSums.add(sum * value);
                long concatValue = Long.parseLong(sum + "" + value);
                newSums.add(concatValue);
            }
            sums = newSums;
        }
        return sums.contains(target);
    }
}

//24556