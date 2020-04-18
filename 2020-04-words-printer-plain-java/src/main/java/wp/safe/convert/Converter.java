package wp.safe.convert;

import wp.safe.core.Words;

public interface Converter {
    Words apply(Words words);

    default Converter and(Converter o) {
        return words -> o.apply(apply(words));
    }
}
