package wp.unsafe;

import wp.safe.convert.Capitalize;
import wp.safe.convert.Compress;
import wp.safe.convert.Converter;
import wp.safe.convert.Reverse;
import wp.safe.core.Word;
import wp.safe.core.Words;
import wp.safe.format.Formatter;
import wp.safe.format.Horizontal;
import wp.safe.format.Vertical;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

class Input {
    static Parsed<List<Converter>> converters(List<Arg> args) {
        List<Converter> xs = args.stream().flatMap(Input::converter).collect(toList());

        if (xs.isEmpty())
            return Parsed.failure("no convert option.");
        else
            return Parsed.success(xs);
    }

    private static Stream<Converter> converter(Arg arg) {
        if (arg.isOption("capitalize"))
            return Stream.of(new Capitalize());

        else if (arg.isOption("reverse"))
            return Stream.of(new Reverse());

        else if (arg.isOption("compress"))
            return Stream.of(new Compress());

        else
            return Stream.empty();
    }

    static Parsed<Formatter> formatter(List<Arg> args) {
        List<Formatter> xs = args.stream().flatMap(Input::formatter).collect(toList());

        if (xs.size() != 1)
            return Parsed.failure("no unique format option.");
        else
            return Parsed.success(xs.get(0));
    }

    private static Stream<Formatter> formatter(Arg arg) {
        if (arg.isOption("horizontal"))
            return Stream.of(new Horizontal());

        else if (arg.isOption("vertical"))
            return Stream.of(new Vertical());

        else
            return Stream.empty();
    }

    static Parsed<Words> words(List<Arg> args) {
        List<Words> xs = args.stream().flatMap(Input::words).collect(toList());

        if (xs.size() != 1)
            return Parsed.failure("no unique arg.");
        else
            return Parsed.success(xs.get(0));
    }

    private static Stream<Words> words(Arg arg) {
        if (arg.notOption())
            if (arg.empty()) {
                return Stream.of(
                        new Words(emptyList())
                );
            } else
                return Stream.of(
                        new Words(
                                Stream.of(arg.v.split(" ")).map(Word::new).collect(toList())
                        )
                );
        else
            return Stream.empty();
    }
}
