package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day05 implements Day {

    @Override
    public int part1(List<String> strings) {
        Map<String, ArrayList<String>> info = getInfo(strings);
        ArrayList<ArrayList<Integer>> pagesMatrix = getPagesMatrix(info.get("pages"));
        ArrayList<ArrayList<Integer>> rulesMatrix = getRulesMatrix(info.get("rules"));

        Map<Integer, List> befores = getBefores(rulesMatrix);
        int count = 0;

        for(ArrayList<Integer> row : pagesMatrix) {
            boolean flag = true;
            for(int i = 0; i < row.size(); i++) {
                for(int j = i; j < row.size(); j++) {
                    if(befores.get(row.get(i)).contains(row.get(j))){
                        flag = false;
                    }
                }
            }
            if (flag) {
                count += row.get(row.size() / 2);
            }
        }
        return count;
    }

    @Override
    public int part2(List<String> strings) {
        Map<String, ArrayList<String>> info = getInfo(strings);
        ArrayList<ArrayList<Integer>> pagesMatrix = getPagesMatrix(info.get("pages"));
        ArrayList<ArrayList<Integer>> rulesMatrix = getRulesMatrix(info.get("rules"));

        Map<Integer, List> befores = getBefores(rulesMatrix);
        int count = 0;

        for(ArrayList<Integer> row : pagesMatrix) {
            boolean flag = false;
            for(int i = 0; i < row.size(); i++) {
                for(int j = i; j < row.size(); j++) {
                    if(befores.get(row.get(i)).contains(row.get(j))){
                        int x=row.get(i);
                        row.set(i,row.get(j));
                        row.set(j,x);
                        flag = true;
                    }
                }
            }
            if (flag) {
                count += row.get(row.size() / 2);
            }
        }
        return count;
    }

    private Map<String, ArrayList<String>> getInfo(List<String> strings) {
        boolean flag = false;
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String string : strings) {
            if (string.isBlank()) {
                flag = true;
                continue;
            }
            if (!flag) {
                rules.add(string);
            }
            if (flag) {
                pages.add(string);
            }
        }
        map.put("rules", rules);
        map.put("pages", pages);
        return map;
    }

    private ArrayList<ArrayList<Integer>> getPagesMatrix(ArrayList<String> pages) {
        ArrayList<ArrayList<Integer>> pagesMatrix = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++) {
            ArrayList<Integer> page = new ArrayList<>();
            String[] split = pages.get(i).split(",");
            for (String s : split) {
                page.add(Integer.parseInt(s));
            }
            pagesMatrix.add(page);
        }
        return pagesMatrix;
    }

    private ArrayList<ArrayList<Integer>> getRulesMatrix(ArrayList<String> rules) {
        ArrayList<ArrayList<Integer>> pagesMatrix = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            ArrayList<Integer> rule = new ArrayList<>();
            String[] split = rules.get(i).split("\\|");
            for (String s : split) {
                rule.add(Integer.parseInt(s));
            }
            pagesMatrix.add(rule);
        }
        return pagesMatrix;
    }

    private Map<Integer, List> getBefores(ArrayList<ArrayList<Integer>> rules) {
        Map<Integer, List> before = new HashMap<>();

        for (ArrayList<Integer> rule : rules) {
            if (before.containsKey(rule.getLast())) {
                before.get(rule.getLast()).add(rule.getFirst());
            } else {
                ArrayList<Integer> newRule = new ArrayList<>();
                newRule.add(rule.getFirst());
                before.put(rule.getLast(), newRule);
            }
        }
        return before;
    }

}
