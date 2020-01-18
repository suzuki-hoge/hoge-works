package list;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@ToString(includeFieldNames = false)
class SortableList<T extends Comparable> {
    private final List<T> ts;

    @SafeVarargs
    static <T extends Comparable> SortableList<T> of(T... ts) {
        return new SortableList<>(stream(ts).collect(toList()));
    }

    <R extends Comparable> SortableList<R> map(Function<T, R> f) {
        return new SortableList<>(ts.stream().map(f).collect(toList()));
    }

    SortableList<T> sort() {
        return new SortableList<>(ts.stream().sorted().collect(toList()));
    }

    public static void main(String[] args) {
        SortableList<Integer> list = SortableList.of(4, 2, 5, 1, 3);
        list.map(Item::new).sort(); // SortableList(Item(1), Item(2), Item(3), Item(4), Item(5))
    }
}
