package blocks;

import core.Data;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static util.ListUtil.join;

public class DstParser {
    public static List<String> parse(Data d) {
        List<List<String>> blocks = d.getRs().stream()
                .map(
                        r -> zip(
                                DstKeysParser.parse(r),
                                DstValuesParser.parse(r)
                        )
                )
                .collect(toList());

        return join(blocks, "");
    }

    private static List<String> zip(List<String> ks, List<String> vs) {
        return Stream.iterate(0, n -> n + 1)
                .limit(ks.size())
                .map(n -> format("%s=%s", ks.get(n), vs.get(n)))
                .collect(toList());
    }
}
