package wp;

import javaslang.collection.List;
import javaslang.control.Option;

import static javaslang.control.Option.none;
import static javaslang.control.Option.some;

public interface Formatter {
    String apply(List<String> words);

    static Option<Formatter> match(String arg) {
        return arg.equals("--horizontal") ? some(Formatter::horizontal) : none();
    }

    static String horizontal(List<String> words) {
        return words.mkString(" ");
    }
}
