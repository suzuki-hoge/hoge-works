package comma_separated;

import core.Element;
import core.Key;
import core.Record;

import static java.util.stream.Collectors.joining;

public class DstKeysParser {
    public static String parse(Record r) {
        return r.getEs().stream()
                .map(Element::getK)
                .map(DstKeysParser::spacedLower)
                .collect(joining(","));
    }

    private static String spacedLower(Key k) {
        return k.getWords().stream()
                .map(String::toLowerCase)
                .collect(joining(" "));
    }
}
