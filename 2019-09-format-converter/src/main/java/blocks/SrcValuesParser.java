package blocks;

import core.Value;

import static util.ListUtil.splitAndTrim;

public class SrcValuesParser {
    public static Value parse(String s) {
        return new Value(splitAndTrim(s, "=").get(1));
    }
}
