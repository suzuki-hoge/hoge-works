package blocks;

import core.Key;

import java.util.function.Function;

import static util.ListUtil.split;
import static util.ListUtil.splitAndTrim;

public class SrcKeysParser {
    public static Key parse(String s) {
        String left = splitAndTrim(s, "=").get(0);

        return Function.<String>identity()
                .andThen(word -> word.replaceAll("([A-Z])", "_$1"))
                .andThen(word -> word.replaceAll("([0-9])", "_$1"))
                .andThen(String::toLowerCase)
                .andThen(word -> split(word, "_"))
                .andThen(Key::new)
                .apply(left);
    }
}
