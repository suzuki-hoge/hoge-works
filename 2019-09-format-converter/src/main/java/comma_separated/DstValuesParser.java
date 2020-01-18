package comma_separated;

import core.Element;
import core.Record;
import core.Value;

import static java.util.stream.Collectors.joining;

public class DstValuesParser {
    public static String parse(Record r) {
        return r.getEs().stream()
                .map(Element::getV)
                .map(Value::getS)
                .collect(joining(","));
    }
}
