package wp.unsafe;

import wp.safe.convert.Converter;
import wp.safe.core.Words;
import wp.safe.format.Formatter;

import java.util.List;
import java.util.Optional;

class Input {
    static Parsed<List<Converter>> converters(List<Arg> args) {
        return null; // todo impl
    }

    private static Optional<Converter> converter(Arg arg) {
        return null; // todo impl
    }

    static Parsed<Formatter> formatter(List<Arg> args) {
        return null; // todo impl
    }

    private static Optional<Formatter> formatter(Arg arg) {
        return null; // todo impl
    }

    static Parsed<Words> words(List<Arg> args) {
        return null; // todo impl
    }

    private static Optional<Words> words(Arg arg) {
        return null; // todo impl
    }
}
