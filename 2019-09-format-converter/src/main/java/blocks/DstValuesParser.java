package blocks;

import core.Element;
import core.Record;
import core.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DstValuesParser {
    public static List<String> parse(Record r) {
        return r.getEs().stream()
                .map(Element::getV)
                .map(Value::getS)
                .collect(toList());
    }
}
