package either;

import java.util.function.Function;

public interface Either<L, R> {
    static <L, R> Either<L, R> r(R r) {
        return new Right<>(r);
    }

    static <L, R> Either<L, R> l(L l) {
        return new Left<>(l);
    }

    <Y> Either<L, Y> rmap(Function<R, Y> f);

    <X> Either<X, R> lmap(Function<L, X> f);

    <Z> Z fold(Function<L, Z> lf, Function<R, Z> rf);

    static void main(String[] args) {
        Either<String, Integer> either1 = Either.r(3);
        either1.rmap(r -> r + 2).lmap(l -> l + "!").fold(Function.identity(), r -> "result is " + r); // result is 5

        Either<String, Integer> either2 = Either.l("error");
        either2.rmap(r -> r + 2).lmap(l -> l + "!").fold(Function.identity(), r -> "result is " + r); // error!
    }
}
