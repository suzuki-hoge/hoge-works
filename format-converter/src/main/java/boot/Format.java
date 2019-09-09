package boot;

import core.Data;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public enum Format {
    C(comma_separated.SrcParser::parse, comma_separated.DstParser::parse),
    T(labeled_tab_separated.SrcParser::parse, labeled_tab_separated.DstParser::parse),
    B(blocks.SrcParser::parse, blocks.DstParser::parse);

    private final Function<List<String>, Data> srcParser;
    private final Function<Data, List<String>> dstParser;

    public static Function<List<String>, List<String>> combine(Format src, Format dst) {
        return src.srcParser.andThen(dst.dstParser);
    }
}

