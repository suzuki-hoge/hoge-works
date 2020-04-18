package wp.safe.convert;

import lombok.AllArgsConstructor;
import wp.safe.core.Word;
import wp.safe.core.Words;

@AllArgsConstructor
public class Capitalize implements Converter {
    @Override
    public Words apply(Words words) {
        return words.map(Word::capitalize);
    }
}
