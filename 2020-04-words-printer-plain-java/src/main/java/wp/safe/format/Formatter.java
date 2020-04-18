package wp.safe.format;

import wp.safe.core.Line;
import wp.safe.core.Words;

public interface Formatter {
    Line apply(Words words);
}
