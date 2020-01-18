# Indent Html
数クラスからなる基礎コーディング力を向上させるためのお題を用意した。

仕様を本ページ上部に、学習の観点と計画を本ページ下部に記載する。

## 仕様
以下のような構造のインスタンスを生成し、html に変換し標準出力する。

```scala
Div("#content .c1 .c2",
  Span("bla bla bla"),
  Div(".c1",
    Div(".c2",
      Span("pon pon pon")
    )
  ),
  Ul(".nav",
    Li(".nav-item", Span("#item1", "bla bla bla")),
    Li(".nav-item", Span("#item2", "pon pon pon"))
  )
)
```

```html
<div id="content" class="c1 c2">
  <span>bla bla bla</span>
  <div class="c1">
    <div class="c2">
      <span>pon pon pon</span>
    </div>
  </div>
  <ul class="nav">
    <li class="nav-item">
      <span id="item1">bla bla bla</span>
    </li>
    <li class="nav-item">
      <span id="item2">pon pon pon</span>
    </li>
  </ul>
</div>
```

上記コードはあくまで一例であり、言語やデータ構造は問わない。

## タグ仕様
### 要素
#### div ( block element )
```html
<div>
</div>
```

任意数の block element, inline element を含むことができる

#### span ( inline element )
```html
<span>bla bla bla</span>
```

#### ul, li ( block element )
```html
<ul>
  <li>
    <span>bla bla bla</span>
  </li>
  <li>
    <span>pon pon pon</span>
  </li>
</ul>
```

`<ul>`は 1 つ以上の`<li>`を、  
`<li>`は任意数の block element, inline element を含むことができる

### 属性
#### id
最大 1 つの`id`属性を指定できる

```html
<span id="foo">bla bla bla</span>
```

#### class
任意数の`class`属性を指定できる

```html
<span class="foo bar">bla bla bla</span>
```

## 学習計画
+ 新たなプロジェクトを作成し、github のリポジトリも新たに作る
+ テストコードを書いて動作保証をすること
+ 長くとも 1 週間かつ 10 時間程度に目標を定め、作り上げることを目指すのが望ましい
+ 学習終了時にふりかえりをし、経過時間 / うまくいった点 / うまくいかなかった点等を残す
+ 半年〜１年のち、再学習をするのが望ましい
+ 極力多くを標準入出力ではなくテストコードで確認できる様に考え設計するのが望ましい
+ `<ul>`における`<li>`の数等の制約は極力コンパイルチェックで担保できる様に考え設計するのが望ましい
+ 必要であれば事前に再帰やコンポジットパターンについて学習しておくこと

## 学習観点
+ プロジェクト作成
+ 依存ライブラリのセットアップ
+ 配列操作
+ データ構造設計
+ 再帰・コンポジットパターン
+ 動くものを作りきる
+ git

