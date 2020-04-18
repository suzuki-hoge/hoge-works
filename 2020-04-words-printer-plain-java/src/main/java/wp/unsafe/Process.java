package wp.unsafe;

import wp.safe.convert.Converter;
import wp.safe.core.Line;
import wp.safe.core.Words;
import wp.safe.format.Formatter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Process {
    public static String apply(List<String> args_) {
        List<Arg> args = args_.stream().map(Arg::new).collect(toList());

        Parsed<List<Converter>> converters = Input.converters(args);
        Parsed<Formatter> formatter = Input.formatter(args);
        Parsed<Words> words = Input.words(args);

        return converters.flatMap(
                cs -> formatter.flatMap(
                        f -> words.map(
                                ws -> apply(cs, f, ws)
                        )
                )
        ).fold(
                line -> line.v,
                error -> "error: " + error
        );
    }

    private static Line apply(List<Converter> cs, Formatter f, Words ws) {
        Converter c = cs.stream().reduce(Converter::and).get();

        return f.apply(c.apply(ws));
    }
}
