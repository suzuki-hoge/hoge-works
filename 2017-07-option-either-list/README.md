# Option Either List
簡易なジェネリクスを用いるメソッドを実装するお題を用意した。

仕様を本ページ上部に、追加仕様を別ページに、学習の観点と計画を本ページ下部に記載する。

## 仕様
### step 01: Option
`Option#of`と`map`と`filter`を実装せよ。

```java
Option<Integer> option1 = Option.of(3);

option1
    .map(t -> t + 2);                // Some(5)
```

```java
Option<Integer> option2 = Option.of(null);

option2
    .map(t -> t + 2);                // None
```

### step 02: Either
`Either#r`, `Either#l`と`rmap`と`lmap`と`fold`を実装せよ。

`Either`は`Option`の`None`に相当する場合にエラーメッセージ等を持てる型である。

`rmap`は右側に、`lmap`は左側に変換をかけ、`fold`は両方に変換をかけ値を手に入れる処理である。

```java
Either<String, Integer> either1 = Either.r(3);

either1
    .rmap(r -> r + 2)
    .lmap(l -> l + "!")
    .fold(identity(), r -> "result is " + r);    // result is 5
```

```java
Either<String, Integer> either2 = Either.l("error");

either2
    .rmap(r -> r + 2)
    .lmap(l -> l + "!")
    .fold(identity(), r -> "result is " + r);    // error!
```

### step 03: SortableList
`List#of`と`map`と`sort`を実装せよ。

ただしこの`SortableList`は`Comparable`を実装したクラスしか要素にできない様にせよ。

```java
SortableList<Integer> list = SortableList.of(4, 2, 5, 1, 3);

list
    .map(Item::new)
    .sort();           // SortableList(Item(1), Item(2), Item(3), Item(4), Item(5))
```

```java
public class Item implements Comparable { ... }
```

`SortableList`の内部では`java`標準の`Array`や`List`を用いて良いものとする。

## 学習計画
+ 新たなプロジェクトを作成し、github のリポジトリも新たに作る
+ git を用い、最大でも step ごとにコミットを切ること
+ テストコードを書いて動作保証をすること
+ groovy はジェネリクスとラムダ式の扱いが難しいので、jUnit でテストを書くことを推奨する
+ 長くとも 1 週間かつ 10 時間程度に目標を定め、全 step を実装することを目指すのが望ましい

## 学習観点
+ プロジェクト作成
+ 依存ライブラリのセットアップ
+ ジェネリクス
+ ラムダ式
+ ソート
+ git

