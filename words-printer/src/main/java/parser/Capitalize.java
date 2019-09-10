package parser;

import java.util.stream.Collectors;

public class Capitalize {
    public static final Parser parse = ss -> ss.stream()
            .map(Capitalize::capitalize)
            .collect(Collectors.toList());

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
