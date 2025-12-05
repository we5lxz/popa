package n3;

import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

// Абстракции
interface TextProvider {
    String getText() throws Exception;
}

interface CharFrequencyCounter {
    Map<Character, Long> count(String text);
}

interface SymbolFinder {
    Map.Entry<Character, Long> find(Map<Character, Long> freq, long target);
}

// Реализации
class FileTextProvider implements TextProvider {
    private final List<String> files;
    public FileTextProvider(List<String> files) { this.files = files; }

    @Override
    public String getText() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String f : files) {
            sb.append(Files.readString(Path.of(f)));
        }
        return sb.toString();
    }
}

class InlineTextProvider implements TextProvider {
    private final String text;
    public InlineTextProvider(String text) { this.text = text; }
    @Override
    public String getText() { return text; }
}

class DummyTextProvider implements TextProvider {
    @Override
    public String getText() { return "тестовый текст"; }
}

class StreamCharCounter implements CharFrequencyCounter {
    @Override
    public Map<Character, Long> count(String text) {
        return text.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}

class LoopCharCounter implements CharFrequencyCounter {
    @Override
    public Map<Character, Long> count(String text) {
        Map<Character, Long> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0L) + 1);
        }
        return map;
    }
}

class MapCharCounter implements CharFrequencyCounter {
    @Override
    public Map<Character, Long> count(String text) {
        return text.chars().boxed()
                .collect(Collectors.toMap(
                        i -> (char)i.intValue(),
                        i -> 1L,
                        Long::sum
                ));
    }
}

class TargetSymbolFinder implements SymbolFinder {
    @Override
    public Map.Entry<Character, Long> find(Map<Character, Long> freq, long target) {
        return freq.entrySet().stream()
                .filter(e -> e.getValue() <= target)
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }
}

class ClosestBelowFinder implements SymbolFinder {
    @Override
    public Map.Entry<Character, Long> find(Map<Character, Long> freq, long target) {
        Map.Entry<Character, Long> best = null;
        long bestVal = -1;
        for (var e : freq.entrySet()) {
            if (e.getValue() <= target && e.getValue() > bestVal) {
                best = e;
                bestVal = e.getValue();
            }
        }
        return best;
    }
}

class DummyFinder implements SymbolFinder {
    @Override
    public Map.Entry<Character, Long> find(Map<Character, Long> freq, long target) {
        return freq.entrySet().stream().findFirst().orElse(null);
    }
}

// Main
public class popa2_2 {
    public static void main(String[] args) throws Exception {

        // Источник текста
        TextProvider provider = new FileTextProvider(
                List.of(
                        "C:/Users/Соня/IdeaProjects/popa/src/n3/page1.txt",
                        "C:/Users/Соня/IdeaProjects/popa/src/n3/page2.txt"
                )
        );

        String text = provider.getText();

        // Подсчёт частоты символов
        CharFrequencyCounter counter = new StreamCharCounter();
        Map<Character, Long> freq = counter.count(text);

        long max = freq.values().stream().mapToLong(v -> v).max().orElse(0);
        long min = freq.values().stream().mapToLong(v -> v).min().orElse(0);
        long target = (max - min) / 2;

        // Поиск символа
        SymbolFinder finder = new TargetSymbolFinder();
        Map.Entry<Character, Long> result = finder.find(freq, target);

        String out = "MAX=" + max + "\nMIN=" + min + "\nTARGET=" + target + "\n";
        if (result != null) {
            out += "FOUND='" + result.getKey() + "' count=" + result.getValue();
        } else {
            out += "FOUND not found";
        }

        Files.writeString(Path.of("result.txt"), out);
        System.out.println("все топчик работает");
    }
}
