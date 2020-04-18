package wp.safe.core;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class Words {
    private final List<Word> vs;

    public Words map(Function<Word, Word> f) {
        return null; // todo impl
    }

    public Words reverse() {
        return null; // todo impl
    }
}
