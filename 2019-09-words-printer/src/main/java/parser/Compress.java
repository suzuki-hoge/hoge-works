package parser;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Compress {
    public static final Parser parse = ss -> ss.stream()
            .map(Compress::compress)
            .collect(Collectors.toList());

    private static String compress(String s) {
        return Stream.of(s.split(""))
                .reduce(
                        s.substring(0, 1),
                        (acc, _s) -> acc.substring(acc.length() - 1).equals(_s) ? acc : acc + _s
                );
    }
}
