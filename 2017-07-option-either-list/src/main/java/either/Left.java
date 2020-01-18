package either;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Left<L, R> implements Either<L, R> {
    private final L l;

    @Override
    public <Y> Either<L, Y> rmap(Function<R, Y> f) {
        return new Left<>(l);
    }

    @Override
    public <X> Either<X, R> lmap(Function<L, X> f) {
        return new Left<>(f.apply(l));
    }

    @Override
    public <Z> Z fold(Function<L, Z> lf, Function<R, Z> rf) {
        return lf.apply(l);
    }
}
