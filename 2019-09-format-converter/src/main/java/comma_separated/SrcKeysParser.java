package comma_separated;

import core.Key;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.ListUtil.split;
import static util.ListUtil.splitAndTrim;

public class SrcKeysParser {
    public static List<Key> parse(String s) {
        return splitAndTrim(s, ",").stream()
                .map(word -> split(word, " "))
                .map(Key::new)
                .collect(toList());
    }
}
