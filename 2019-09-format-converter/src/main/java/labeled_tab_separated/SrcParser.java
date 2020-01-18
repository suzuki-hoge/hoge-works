package labeled_tab_separated;

import core.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SrcParser {
    public static Data parse(List<String> ss) {
        return new Data(
                ss.stream()
                        .map(s -> {
                            List<Key> ks = SrcKeysParser.parse(s);
                            List<Value> vs = SrcValuesParser.parse(s);

                            return zip(ks, vs);
                        })
                        .map(Record::new)
                        .collect(toList())
        );
    }

    private static List<Element> zip(List<Key> ks, List<Value> vs) {
        return Stream.iterate(0, n -> n + 1)
                .limit(ks.size())
                .map(n -> new Element(ks.get(n), vs.get(n)))
                .collect(toList());
    }
}
