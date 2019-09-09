package labeled_tab_separated;

import core.Key;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.ListUtil.split;
import static util.ListUtil.splitAndTrim;

public class SrcKeysParser {
    public static List<Key> parse(String s) {
        return splitAndTrim(s, "\t").stream()
                .map(e -> splitAndTrim(e, ":").get(0))
                .map(word -> split(word, "-"))
                .map(Key::new)
                .collect(toList());
    }
}
