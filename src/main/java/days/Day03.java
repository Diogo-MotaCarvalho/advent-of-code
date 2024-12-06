package days;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day03 implements Day {
    private static final String mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)";
    private static final String doRegex = "(do\\(\\))";
    private static final String dontRegex = "don't\\(\\)";
    //"do(?:n't)?\\(\\)"

    @Override
    public int part1(List<String> strings) {
        Pattern mul = Pattern.compile(mulRegex, Pattern.CASE_INSENSITIVE);
        AtomicInteger count = new AtomicInteger();
        for (String string : strings) {
            Matcher matcher = mul.matcher(string);
            while (matcher.find()) {
                String number = matcher.group().substring(4, matcher.group().length() - 1);
                String[] split = number.split(",");
                count.addAndGet(Integer.parseInt(split[0]) * Integer.parseInt(split[1]));
            }
        }
        return count.get();
    }

    @Override
    public int part2(List<String> strings) {

        Pattern mulPattern = Pattern.compile(mulRegex, Pattern.CASE_INSENSITIVE);
        Pattern dontPattern = Pattern.compile(dontRegex, Pattern.CASE_INSENSITIVE);
        Pattern doPattern = Pattern.compile(doRegex, Pattern.CASE_INSENSITIVE);

        boolean doFlag = true;
        int count = 0;

        for (String string : strings) {
            Matcher doMatcher = doPattern.matcher(string);
            Matcher dontMatcher = dontPattern.matcher(string);
            Matcher mulMatcher = mulPattern.matcher(string);

            int index = 0;
            while (index < string.length()) {
                if (doMatcher.find(index) && doMatcher.start() == index) {
                    doFlag = true;
                    index = doMatcher.end();
                } else if (dontMatcher.find(index) && dontMatcher.start() == index) {
                    doFlag = false;
                    index = dontMatcher.end();
                } else if (mulMatcher.find(index) && mulMatcher.start() == index) {
                    if (doFlag) {
                        String number = mulMatcher.group().substring(4, mulMatcher.group().length() - 1);
                        String[] split = number.split(",");
                        count += Integer.parseInt(split[0])  * Integer.parseInt(split[1]);
                    }
                    index = mulMatcher.end();
                } else {
                    index++;
                }
            }
        }
        return count;
    }
}
