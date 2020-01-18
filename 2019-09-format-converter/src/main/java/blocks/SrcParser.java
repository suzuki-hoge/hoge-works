package blocks;

import core.Data;
import core.Element;
import core.Record;
import util.ListUtil;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SrcParser {
    public static Data parse(List<String> ss) {
        return new Data(
                ListUtil.blocked(ss, "").stream()
                        .map(
                                block -> new Record(
                                        block.stream()
                                                .map(
                                                        s -> new Element(
                                                                SrcKeysParser.parse(s),
                                                                SrcValuesParser.parse(s)
                                                        )
                                                )
                                                .collect(toList())
                                )
                        ).collect(toList())
        );
    }
}
