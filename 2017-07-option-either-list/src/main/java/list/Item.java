package list;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Item implements Comparable {
    private final int v;

    @Override
    public int compareTo(Object o) {
        return Integer.compare(v, ((Item) o).v);
    }
}
