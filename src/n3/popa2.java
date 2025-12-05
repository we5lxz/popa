package n3;

import java.nio.file.*;      // подключаем штуку, чтобы читать файлы
import java.util.*;          // коллекции
import java.util.stream.*;

public class popa2 {
    public static void main(String[] args) throws Exception {


        String aaaa = Files.readString(Path.of("C:/Users/Соня/IdeaProjects/popa/src/n3/page1.txt"));
        String bbbb = Files.readString(Path.of("C:/Users/Соня/IdeaProjects/popa/src/n3/page2.txt"));
        String omg = aaaa + bbbb;

        // создаём мапу "символ → сколько раз встречается"
        // короче это все группирует по символам и считает сколько раз оно встречается
        Map<Character, Long> cringe = omg.chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(
                        x -> x,
                        Collectors.counting()
                ));


        long MAXX = cringe.values().stream()
                .mapToLong(z -> z)
                .max()
                .orElse(0);
        long MINN = cringe.values().stream()
                .mapToLong(z -> z)
                .min()
                .orElse(0);
        long omgtarget = (MAXX - MINN) / 2;


        char wow = 0;        // символ не найден → ставим 0

        // здесь количество найденных повтолрений лежит а там сверху число ечли что
        long wowcnt = -999;

        // проходим по каждому символу и смотрим подходит ли он под условие
        for (var pp : cringe.entrySet()) {
            if (pp.getValue() <= omgtarget
                    && pp.getValue() > wowcnt) {
                wow = pp.getKey();
                wowcnt = pp.getValue();
            }
        }

        String kek = "MAX=" + MAXX + "\n" +
                "MIN=" + MINN + "\n" +
                "TARGET=" + omgtarget + "\n" +
                "FOUND='" + wow + "' count=" + wowcnt + "\n";
        Files.writeString(Path.of("result.txt"), kek);
        System.out.println("все топчик работает");
    }
}
