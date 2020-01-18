package option;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Option<T> {
    static <T> Option<T> of(T t) {
        return t == null ? new None<T>() : new Some<>(t);
    }

    <R> Option<R> map(Function<T, R> f);

    Option<T> filter(Predicate<T> f);

    static void main(String[] args) {
        Option<Integer> option1 = Option.of(3);
        option1.map(t -> t + 2); // Some(5)

        Option<Integer> option2 = Option.of(null);
        option2.map(t -> t + 2); // None
    }
}
