package wp;

import javaslang.collection.List;
import javaslang.control.Validation;
import lombok.val;

import static javaslang.control.Option.none;
import static javaslang.control.Option.some;
import static javaslang.control.Validation.invalid;
import static javaslang.control.Validation.valid;

interface Input {
    static Validation<String, List<Converter>> converters(List<String> args) {
        val parsed = args.flatMap(Converter::match);

        if (parsed.nonEmpty())
            return valid(parsed);
        else
            return invalid("no convert option.");
    }

    static Validation<String, Formatter> formatter(List<String> args) {
        val parsed = args.flatMap(Formatter::match);

        if (parsed.length() == 1)
            return valid(parsed.head());
        else
            return invalid("no unique format option.");
    }

    static Validation<String, List<String>> words(List<String> args) {
        val parsed = args.flatMap(arg -> arg.startsWith("--") ? none() : some(arg));

        if (parsed.length() == 1)
            return valid(List.of(parsed.head().split(" ")));
        else
            return invalid("no unique words.");
    }
}
