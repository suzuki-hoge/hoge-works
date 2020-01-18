package option;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
@ToString(includeFieldNames = false)
public class None<T> implements Option<T> {
    @Override
    public <R> Option<R> map(Function<T, R> f) {
        return new None<>();
    }

    @Override
    public Option<T> filter(Predicate<T> f) {
        return new None<>();
    }
}
