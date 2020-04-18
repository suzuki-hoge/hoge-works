package wp.unsafe;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
class Parsed<T> {
    private final List<String> errors;
    private final T t;

    static <T> Parsed<T> success(T t) {
        return null; // todo impl
    }

    static <T> Parsed<T> failure(String s) {
        return null; // todo impl
    }

    <R> Parsed<R> map(Function<T, R> f) {
        return null; // todo impl
    }

    <R> Parsed<R> flatMap(Function<T, Parsed<R>> f) {
        return null; // todo impl
    }

    <R> R fold(Function<T, R> onSuccess, Function<List<String>, R> onFailure) {
        return null; // todo impl
    }
}
