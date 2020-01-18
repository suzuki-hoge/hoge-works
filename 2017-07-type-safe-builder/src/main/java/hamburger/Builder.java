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

    MainFixed てりやき() {
        _main = "てりやき";
        return new MainFixed();
    }

    MainFixed チーズ() {
        _main = "チーズ";
        return new MainFixed();
    }

    class MainFixed {
        DrinkFixed オレンジ() {
            _drink = "オレンジ";
            return new DrinkFixed();
        }

        DrinkFixed コーヒー(String... options) {
            _drink = "コーヒー";
            _options = List.of(options);
            return new DrinkFixed();
        }
    }

    class DrinkFixed {
        SidesFixed なし() {
            return new SidesFixed();
        }

        SidesFixed サイド(String first, String... remains) {
            _sides = List.of(first).appendAll(List.of(remains));
            return new SidesFixed();
        }
    }

    class SidesFixed {
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
}
