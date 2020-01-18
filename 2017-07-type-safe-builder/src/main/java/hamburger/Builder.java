package hamburger;

import javaslang.collection.List;
import lombok.NoArgsConstructor;

import static java.lang.String.format;
import static javaslang.collection.List.empty;

@NoArgsConstructor(staticName = "init")
class Builder {
    private String _main;
    private String _drink;
    private List<String> _options = empty();
    private List<String> _sides = empty();

    Builder main(String v) {
        _main = v;
        return this;
    }

    Builder drink(String v) {
        _drink = v;
        return this;
    }

    Builder options(String... vs) {
        _options = List.of(vs);
        return this;
    }

    Builder sides(String... vs) {
        _sides = List.of(vs);
        return this;
    }

    String build() {
        return format(
                "{ 'main': '%s', 'drink': '%s', 'options': [%s], 'sides': [%s] }",
                _main,
                _drink,
                _options.map(it -> format("'%s'", it)).mkString(", "),
                _sides.map(it -> format("'%s'", it)).mkString(", ")
        );
    }
}
