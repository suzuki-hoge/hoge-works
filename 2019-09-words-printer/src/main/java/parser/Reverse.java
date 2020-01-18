package parser;

import java.util.Collections;

public class Reverse {
    public static final Parser parse = ss -> {
        Collections.reverse(ss);

        return ss;
    };
}
