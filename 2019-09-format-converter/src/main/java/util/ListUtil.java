package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class ListUtil {
    public static List<String> split(String s, String separator) {
        return Stream.of(s.split(separator))
                .collect(toList());
    }

    public static List<String> splitAndTrim(String s, String separator) {
        return Stream.of(s.split(separator))
                .map(String::trim)
                .collect(toList());
    }

    public static <T> T head(List<T> ts) {
        return ts.get(0);
    }

    public static <T> List<T> tail(List<T> ts) {
        return ts.isEmpty() ? emptyList() : ts.subList(1, ts.size());
    }

    public static <T> List<T> init(List<T> ts) {
        return ts.isEmpty() ? emptyList() : ts.subList(0, 1);
    }

    public static <T> List<List<T>> blocked(List<T> ts, T separator) {
        ArrayList<ArrayList<T>> head = new ArrayList<>();
        head.add(new ArrayList<>());

        ArrayList<ArrayList<T>> accumulated = ts.stream().reduce(
                head,
                (acc, t) -> {
                    if (t.equals(separator)) {
                        acc.add(new ArrayList<T>());
                        return acc;
                    } else {
                        acc.get(acc.size() - 1).add(t);
                        return acc;
                    }

                },
                (tss1, tss2) -> null // fxxkin' argument
        );

        return accumulated.stream()
                .map(ArrayList::new)
                .collect(toList());
    }

    public static <T> List<T> join(List<List<T>> tss, T separator) {
        return tss.stream()
                .reduce(
                        (acc, ts) -> {
                            acc.add(separator);
                            acc.addAll(ts);
                            return acc;
                        }
                ).get();
    }
}
