# Word Printer
paiza や codewars の様な１クラスかつ最初からある程度使うメソッドの目安が付く様なお題とは別に、小中規模の要件を０から作り上げる経験も必要なので、そのお題を用意した。

仕様を本ページ上部に、学習の観点と計画を本ページ下部に記載する。

## 仕様
引数で渡された`半角スペース`区切りの文書を、変換し出力する。

### 実行
```
$ words-printer "this book is so funny" --capitalize  --vertical

This
Book
Is
So
Funny
```

の様な形で実行する。

コマンド名は細かくは何でも良い。

### 基本仕様
+ 最低１つの変換ルールと、１つの出力ルールを指定する
  + それぞれのオプションはどの位置に指定しても動くこと
+ 変換結果は標準出力をする

### 変換ルール
#### capitalize
各単語の先頭１文字を大文字にする。

`this book is so funny` → `This Book Is So Funny`
  
#### reverse
単語の順番を逆順にする。

`this book is so funny` → `funny so is book this`
  
#### compress
各単語の中の連続する文字を１文字にする。

`this book is so funny` → `this bok is so funy`

### 出力ルール
#### horizontal
単語を`半角スペース`で区切って出力する。

#### vertical
単語を`改行`で区切って出力する。

### 実行例
```
$ words-printer "this book is so funny" --compress --horizontal

this bok is so funy
```

```
$ words-printer "this book is so funny" --vertial --compress --reverse -- capitalize

Funy
So
Is
Bok
This
```
  
## 学習計画

## 学習観点
