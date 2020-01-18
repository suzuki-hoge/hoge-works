package comma_separated;

import core.Data;
import core.Record;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static util.ListUtil.init;

public class DstParser {
    public static List<String> parse(Data d) {
        List<Record> r = init(d.getRs());

        List<Record> rs = d.getRs();

        return concat(
                r.stream().map(DstKeysParser::parse),
                rs.stream().map(DstValuesParser::parse)
        )
                .collect(toList());
    }
}
