package labeled_tab_separated;

import core.Element;
import core.Key;
import core.Record;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DstKeysParser {
    public static List<String> parse(Record r) {
        return r.getEs().stream()
                .map(Element::getK)
                .map(DstKeysParser::hyphenedLower)
                .collect(toList());
    }

    private static String hyphenedLower(Key k) {
        return k.getWords().stream()
                .map(String::toLowerCase)
                .collect(joining("-"));
    }
}
