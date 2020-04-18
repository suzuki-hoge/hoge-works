package wp.safe.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Words {
    private final List<Word> vs;

    public Words map(Function<Word, Word> f) {
        return new Words(
                vs.stream().map(f).collect(toList())
        );
    }

    public Words reverse() {
        List<Word> vs_ = new ArrayList<>(vs);
        Collections.reverse(vs_);
        return new Words(vs_);
    }

    public List<String> raw() {
        return vs.stream().map(w -> w.v).collect(toList());
    }
}
