package days;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Objects;

public class Day01 implements Day {

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    @Override
    public int part1(List<String> strings) {

        for (String s : strings) {
            String[] split = s.split("   ");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }

        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        Iterator<Integer> leftIterator = left.iterator();
        Iterator<Integer> rightIterator = right.iterator();

        int distance=0;

        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            distance += Math.abs(leftIterator.next() - rightIterator.next());
        }

        return distance;
    }

    @Override
    public int part2(List<String> strings) {

        for (String s : strings) {
            String[] split = s.split("   ");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }

        int value=0;

        for(Integer left : left) {
            int count=0;
            for(Integer right : right) {
                if(left.equals(right)) {
                    count++;
                }
            }
            value += left * count;
        }

        return value;
    }
}
