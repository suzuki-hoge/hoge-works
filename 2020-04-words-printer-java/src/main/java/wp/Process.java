package wp;

import javaslang.collection.List;
import lombok.val;

import static java.util.function.Function.identity;

interface Process {
    static String apply(List<String> args) {
        val converters = Input.converters(args);
        val formatter = Input.formatter(args);
        val words = Input.words(args);

        return converters.flatMap(
                cs -> formatter.flatMap(
                        f -> words.map(
                                ws -> f.apply(
                                        cs.foldLeft(ws, (acc, c) -> c.apply(acc))
                                )
                        )
                )
        ).fold(e -> "error: " + e, identity());
    }
}
