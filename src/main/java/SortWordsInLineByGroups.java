import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SortWordsInLineByGroups {

    private static Logger log = LoggerFactory.getLogger(SortWordsInLineByGroups.class);

    private static int partition(LinkedList<String> lines, int left, int right) {
        int pivot = lines.get((right + left) / 2).length();
        while (left <= right) {
            while (lines.get(left).length() > pivot) {
                left++;
            }
            while (lines.get(right).length() < pivot) {
                right--;
            }
            if (left <= right) {
                Collections.swap(lines, right, left);
                left++;
                right--;
            }
        }
        return left;
    }

    private static LinkedList<String> getSortedLinesBySize(LinkedList<String> lines, int left,
                                                           int right) {
        int index;
        if (lines.size() > 1) {
            index = partition(lines, left, right);
            if (left > index) {
                getSortedLinesBySize(lines, left, index - 1);
            }
            if (index - 1 > right) {
                getSortedLinesBySize(lines, index, right);
            }
        }

        return lines;
    }

    private static LinkedList<String> getSortedLinesByNaturalOrder(LinkedList<String> group) {
        HashMap<Integer, LinkedList<String>> groupedLinesBySize = new HashMap<>();
        for (String line : group) {
            groupedLinesBySize.computeIfAbsent(line.length(),
                    k -> new LinkedList<>()).add(line);
        }

        LinkedList<String> sortedLines = new LinkedList<>();
        groupedLinesBySize.forEach((key, value) -> {
            if (value.size() > 1) {
                value.sort(Comparator.naturalOrder());
            }
            if (sortedLines.size() != 0) {
                sortedLines.addAll(0, value);
            } else {
                sortedLines.addAll(value);
            }
        });

        return sortedLines;
    }

    private static HashMap<String, LinkedList<String>> getGroups(String[] inputLine,
                                                                 Set<String> firstCharacters) {
        HashMap<String, LinkedList<String>> groupedLines = new HashMap<>();
        firstCharacters.forEach(character -> {
            LinkedList<String> group = new LinkedList<>();
            HashSet<Integer> wordsLength = new HashSet<>();
            for (String line : inputLine) {
                if (line.indexOf(character) == 0) {
                    group.add(line);
                    wordsLength.add(line.length());
                }
            }
            if (group.size() > 1) {
                LinkedList<String> sortedGroup =  getSortedLinesBySize(group, 0,
                        group.size() - 1);
                if (wordsLength.size() == group.size()) {
                    groupedLines.put(character, sortedGroup);
                } else {
                    groupedLines.put(character, getSortedLinesByNaturalOrder(sortedGroup));
                }
            }
        });

        return groupedLines;
    }

    private static Set<String> getSetOfFirstCharacters(String[] inputLine) {
        Set<String> firstCharacters = new HashSet<>();
        for (String s : inputLine) {
            firstCharacters.add(s.substring(0, 1));
        }

        return firstCharacters;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Введите строку, слова в которой разделены пробелом: ");
        String inputLine = inputScanner.nextLine();
        try {
            inputLine = inputLine.trim();
            String[] splitted = inputLine.toLowerCase().split(" ");
            HashMap<String, LinkedList<String>> groupedLines = getGroups(splitted,
                    getSetOfFirstCharacters(splitted));
            groupedLines.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .forEachOrdered(System.out::println);
        } catch (StringIndexOutOfBoundsException e) {
            log.error("ERROR: ", e);
        }
    }
}