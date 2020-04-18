package wp.safe.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.stream.Stream;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Word {
    final String v;

    public Word capitalize() {
        return new Word(
                v.substring(0, 1).toUpperCase() + v.substring(1)
        );
    }

    public Word compress() {
        return new Word(
                Stream.of(v.split(""))
                        .reduce(
                                v.substring(0, 1),
                                (acc, s) -> acc.substring(acc.length() - 1).equals(s) ? acc : acc + s
                        )
        );
    }
}
