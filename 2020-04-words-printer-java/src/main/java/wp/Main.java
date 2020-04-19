package wp;

import javaslang.collection.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                Process.apply(List.of("this book is funny", "--capitalize", "--horizontal"))
        );
        System.out.println(
                Process.apply(List.of("this book is funny", "--capitalize"))
        );
        System.out.println(
                Process.apply(List.of("this book is funny", "--horizontal"))
        );
    }
}
