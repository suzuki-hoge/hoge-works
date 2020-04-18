package wp.safe.convert;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import wp.safe.core.Words;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Reverse implements Converter {
    @Override
    public Words apply(Words words) {
        return words.reverse();
    }
}
