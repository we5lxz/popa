package n3;

import java.util.*;
import java.util.stream.*;

public class popa3 {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Тесты TextProvider ===");

        // InlineTextProvider
        TextProvider inline = new InlineTextProvider("abcabc");
        String text = inline.getText();
        System.out.println(text.equals("abcabc") ? "InlineTextProvider OK" : "InlineTextProvider FAIL");

        // DummyTextProvider
        TextProvider dummy = new DummyTextProvider();
        String dummyText = dummy.getText();
        System.out.println(dummyText.equals("тестовый текст") ? "DummyTextProvider OK" : "DummyTextProvider FAIL");


        System.out.println("\n=== Тесты CharFrequencyCounter ===");

        // StreamCharCounter
        CharFrequencyCounter counter1 = new StreamCharCounter();
        Map<Character, Long> freq1 = counter1.count("aaabbc");
        boolean ok1 = freq1.get('a') == 3 && freq1.get('b') == 2 && freq1.get('c') == 1;
        System.out.println(ok1 ? "StreamCharCounter OK" : "StreamCharCounter FAIL");

        // LoopCharCounter
        CharFrequencyCounter counter2 = new LoopCharCounter();
        Map<Character, Long> freq2 = counter2.count("xyzzyx");
        boolean ok2 = freq2.get('x') == 2 && freq2.get('y') == 2 && freq2.get('z') == 2;
        System.out.println(ok2 ? "LoopCharCounter OK" : "LoopCharCounter FAIL");


        System.out.println("\n=== Тесты SymbolFinder ===");

        Map<Character, Long> sampleFreq = new HashMap<>();
        sampleFreq.put('a', 10L);
        sampleFreq.put('b', 5L);
        sampleFreq.put('c', 20L);

        // TargetSymbolFinder
        SymbolFinder finder1 = new TargetSymbolFinder();
        Map.Entry<Character, Long> res1 = finder1.find(sampleFreq, 15);
        boolean ok3 = res1 != null && res1.getKey() == 'a' && res1.getValue() == 10L;
        System.out.println(ok3 ? "TargetSymbolFinder OK" : "TargetSymbolFinder FAIL");

        // ClosestBelowFinder
        SymbolFinder finder2 = new ClosestBelowFinder();
        Map.Entry<Character, Long> res2 = finder2.find(sampleFreq, 12);
        boolean ok4 = res2 != null && res2.getKey() == 'a' && res2.getValue() == 10L;
        System.out.println(ok4 ? "ClosestBelowFinder OK" : "ClosestBelowFinder FAIL");

        // DummyFinder
        SymbolFinder finder3 = new DummyFinder();
        Map.Entry<Character, Long> res3 = finder3.find(sampleFreq, 100);
        boolean ok5 = res3 != null;
        System.out.println(ok5 ? "DummyFinder OK" : "DummyFinder FAIL");

        System.out.println("\n=== все имба все работает ===");
    }
}
