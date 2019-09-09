package labeled_tab_separated;

import core.Data;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

public class DstParser {
    public static List<String> parse(Data d) {
        return d.getRs().stream()
                .map(r -> join(
                        "\t",
                        zip(
                                DstKeysParser.parse(r),
                                DstValuesParser.parse(r)
                        )
                ))
                .collect(toList());
    }

    private static List<String> zip(List<String> ks, List<String> vs) {
        return Stream.iterate(0, n -> n + 1)
                .limit(ks.size())
                .map(n -> format("%s:%s", ks.get(n), vs.get(n)))
                .collect(toList());
    }
}
