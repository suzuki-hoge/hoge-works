package parser;

import java.util.List;

public interface Parser {
    List<String> parse(List<String> ss);

    default Parser and(Parser o) {
        return ss -> o.parse(parse(ss));
    }
}
