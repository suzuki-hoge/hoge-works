import parser.Capitalize;
import parser.Compress;
import parser.Parser;
import parser.Reverse;
import printer.Horizontal;
import printer.Printer;
import printer.Vertical;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        _main("this book is so funny", "--capitalize", "--reverse", "--compress", "--horizontal");
    }

    private static void _main(String... _args) {
        List<String> args = Stream.of(_args).collect(toList());

        List<String> words = Stream.of(args.get(0).split(" "))
                .collect(toList());

        Printer printer = Stream.of("--horizontal", "--vertical")
                .flatMap(option -> findOption(args, option, Main::asPrinter))
                .collect(toList())
                .get(0);

        Parser parser = Stream.of("--capitalize", "--reverse", "--compress")
                .flatMap(option -> findOption(args, option, Main::asParser))
                .reduce(Parser::and).get();

        System.out.println(
                printer.print(
                        parser.parse(words) // Funy So Is Bok This
                )
        );
    }

    private static Printer asPrinter(String s) {
        return s.equals("--horizontal")
                ? Horizontal.print
                : Vertical.print;
    }

    private static Parser asParser(String s) {
        return s.equals("--capitalize")
                ? Capitalize.parse
                : s.equals("--reverse")
                ? Reverse.parse
                : Compress.parse;
    }

    private static <T> Stream<T> findOption(List<String> args, String option, Function<String, T> mapper) {
        return args.stream()
                .filter(s -> s.equals(option))
                .map(mapper);
    }
}
