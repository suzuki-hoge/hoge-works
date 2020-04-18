package wp.unsafe;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
class Parsed<T> {
    private final Optional<String> error;
    private final Optional<T> t;

    static <T> Parsed<T> success(T t) {
        return new Parsed<>(Optional.empty(), Optional.of(t));
    }

    static <T> Parsed<T> failure(String s) {
        return new Parsed<>(Optional.of(s), Optional.empty());
    }

    <R> Parsed<R> map(Function<T, R> f) {
        return new Parsed<>(error, t.map(f));
    }

    <R> Parsed<R> flatMap(Function<T, Parsed<R>> f) {
        if (t.isPresent())
            return f.apply(t.get());
        else
            return new Parsed<>(error, Optional.empty());
    }

    <R> R fold(Function<T, R> onSuccess, Function<String, R> onFailure) {
        if (t.isPresent())
            return onSuccess.apply(t.get());
        else
            return onFailure.apply(error.get());
    }
}
