package option;

import lombok.AllArgsConstructor;
import lombok.ToString;
import sun.reflect.CallerSensitive;

import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Some<T> implements Option<T> {
    private final T t;

    @Override
    public <R> Option<R> map(Function<T, R> f) {
        return new Some<>(f.apply(t));
    }

    @Override
    public Option<T> filter(Predicate<T> f) {
        return f.test(t) ? this : new None<>();
    }
}
