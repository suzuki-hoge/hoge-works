package boot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static boot.Format.*;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
        _main("/tmp/c.txt", "--src=c", "--dst=t");
    }

    private static void _main(String... args) throws IOException {
        Path path = new File(args[0]).toPath();

        Format srcFormat = Stream.of(args)
                .filter(s -> s.contains("--src"))
                .map(s -> s.split("=")[1])
                .map(Main::asFormat)
                .collect(toList()).get(0);

        Format dstFormat = Stream.of(args)
                .filter(s -> s.contains("--dst"))
                .map(s -> s.split("=")[1])
                .map(Main::asFormat)
                .collect(toList()).get(0);

        Format.combine(srcFormat, dstFormat)
                .apply(Files.readAllLines(path))
                .forEach(System.out::println);
    }

    private static Format asFormat(String s) {
        return s.equals("c")
                ? C
                : s.equals("t")
                ? T
                : B;
    }
}
