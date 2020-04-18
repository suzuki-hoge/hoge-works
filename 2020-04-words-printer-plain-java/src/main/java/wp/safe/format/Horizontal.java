package wp.safe.format;

import lombok.AllArgsConstructor;
import wp.safe.core.Line;
import wp.safe.core.Words;

@AllArgsConstructor
public class Horizontal implements Formatter {
    @Override
    public Line apply(Words words) {
        return new Line(
                String.join(" ", words.raw())
        );
    }
}
