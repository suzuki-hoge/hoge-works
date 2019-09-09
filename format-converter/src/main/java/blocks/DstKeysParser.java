package blocks;

import core.Element;
import core.Key;
import core.Record;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static util.ListUtil.*;

public class DstKeysParser {
    public static List<String> parse(Record r) {
        return r.getEs().stream()
                .map(Element::getK)
                .map(DstKeysParser::camelize)
                .collect(toList());
    }

    private static String camelize(Key k) {
        return concat(
                init(k.getWords()).stream(),
                tail(k.getWords()).stream().map(
                        word -> concat(
                                init(split(word, "")).stream().map(String::toUpperCase),
                                tail(split(word, "")).stream()
                        ).collect(joining())
                )
        )
                .collect(joining(""));
    }
}
