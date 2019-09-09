package comma_separated;

import core.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static util.ListUtil.head;
import static util.ListUtil.tail;

public class SrcParser {
    public static Data parse(List<String> ss) {
        List<Key> ks = SrcKeysParser.parse(head(ss));

        List<List<Value>> vss = tail(ss).stream()
                .map(SrcValuesParser::parse)
                .collect(toList());

        List<Record> rs = vss.stream()
                .map(vs -> zip(ks, vs))
                .map(Record::new)
                .collect(toList());

        return new Data(rs);
    }

    private static List<Element> zip(List<Key> ks, List<Value> vs) {
        return Stream.iterate(0, n -> n + 1)
                .limit(ks.size())
                .map(n -> new Element(ks.get(n), vs.get(n)))
                .collect(toList());
    }
}
