package labeled_tab_separated;

import core.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.ListUtil.splitAndTrim;

public class SrcValuesParser {
    public static List<Value> parse(String s) {
        return splitAndTrim(s, "\t").stream()
                .map(e -> splitAndTrim(e, ":").get(1))
                .map(Value::new)
                .collect(toList());
    }
}
