package wp;

import javaslang.collection.List;
import javaslang.control.Option;
import org.apache.commons.lang3.StringUtils;

import static javaslang.control.Option.none;
import static javaslang.control.Option.some;

public interface Converter {
    List<String> apply(List<String> words);

    static Option<Converter> match(String arg) {
        return arg.equals("--capitalize")
                ? some(Converter::capitalize)
                : arg.equals("--reverse")
                ? some(Converter::reverse)
                : none();
    }

    static List<String> capitalize(List<String> words) {
        return words.map(StringUtils::capitalize);
    }

    static List<String> reverse(List<String> words) {
        return words.reverse();
    }
}
