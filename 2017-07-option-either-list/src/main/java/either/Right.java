package either;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Right<L, R> implements Either<L, R> {
    private final R r;

    @Override
    public <Y> Either<L, Y> rmap(Function<R, Y> f) {
        return new Right<>(f.apply(r));
    }

    @Override
    public <X> Either<X, R> lmap(Function<L, X> f) {
        return new Right<>(r);
    }

    @Override
    public <Z> Z fold(Function<L, Z> lf, Function<R, Z> rf) {
        return rf.apply(r);
    }
}
