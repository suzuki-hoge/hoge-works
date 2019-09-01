# Line Editor

## 基本仕様
+ コマンドラインでファイル名を与えて起動する
+ コマンドを入力してファイルを編集する
+ コマンドは繰り返し入力でき、入力のたびに現在の編集内容が出力される
+ 終了コマンドでファイルを保存して終了する

## 詳細仕様
### 起動
`line-editor [file-name]`の様な形で起動する

コマンド名は細かくは何でも良い

### コマンド内容
#### 基本仕様
+ `[command-name]`の前後等は`/`で区切られる
+ 現在の編集内容を表示し、最終行の`> `に続きコマンドを入力する
  + 各行の先頭には`[line-number]: `を表示する
+ 正常なコマンドを入力した場合は、編集内容と次のコマンド入力プロンプト`> `が表示される

#### command-name
+ `append (a)`で示されるコマンドは、`append`か`a`で実行できる
+ コマンドリスト
  + append (a)
  + insert (i)
  + delete (d)
  + move (m)
  + copy (c)
  + substitute (s)
  + undo (u)
  + redo (r)
  + execute (e)
  + write (w)

#### line-number
+ 先頭行は`1`である
+ `$`で最終行を指定できる

#### line-range
+ `[start-line-number](,[end-line-number])`もしくは`%`で表現される
+ `(,[end-line-number])`は省略することができる
+ `,`の前後には任意数の`半角スペース`を入れることができる
+ `[start-line-number]`および`[end-line-number]`は`[line-number]`のルールを踏襲する
+ `%`は`1,$`と同意である

#### text
+ `"` + 任意の文字列 + `"`
  + `/`を含むことができる
  + `\n`等特殊文字は含まない

### コマンド不正
+ コマンドとして不正な場合は、`> `を表示して再度コマンドを受け付ける
+ 存在しない`line-number`を指定した場合もコマンド不正となる

```
$ line-editor hello.txt

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 1 append "# made by suzuki-hoge"
> 
```

#### append (a)
`[line-number]/[command-name]/[text]`

`[line-number]`行目の次の行に`[text]`を挿入する

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 1/append/"# made by suzuki-hoge"

1: welcome to line-editor
2: # made by suzuki-hoge
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> 
```

#### insert (i)
`[line-number]/[command-name]/[text]`

`[line-number]`行目の前の行に`[text]`を挿入する

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 4/i/""

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: 
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> 
```

#### delete (d)
`[line-range]/[command-name]`

`[line-range]`の行を削除する

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 6/d

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 

> 
```

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 4,$/delete

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation

> 
```

#### move (m)
`[line-range]/[command-name]/[line-number]`

`[line-range]`の行を`[line-number]`行目の次の行に移動して挿入する

ただし、以下の場合は不正コマンドとする

+ `[line-number]`を`[line-range]`に含む場合

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 6/m/1

1: welcome to line-editor
2: good practice!
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 

> 
```

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 2, 4 / move / 6

1: welcome to line-editor
2: 
3: good practice!
4: 
5: this work is coding practice with simple line editor creation
6: if you are interested in line editor, check out linux "ed"

> 
```

#### copy (c)
`[line-range]/[command-name]/[line-number]`

`[line-range]`の行を`[line-number]`行目の次の行にコピーして挿入する

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 6/c/1

1: welcome to line-editor
2: good practice!
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> 
```

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 3, 4 / copy / 3

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: if you are interested in line editor, check out linux "ed"
7: 
8: good practice!

> 
```

#### substitute (s)
`[line-range]/[command-name]/[src-text]/[dst-text]`

`[line-range]`の行の`[src-word]`を全て`[dst-word]`に置換する

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 4/s/"ed"/"ed and vi"

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed and vi"
5: 
6: good practice!

> 
```

#### undo (u)
`[command-name]`

直前の編集を戻す

ただし、以下の場合は不正コマンドとする

+ 起動直後の場合
+ `undo`直後の場合
+ `redo`直後の場合

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 1/append/"# made by suzuki-hoge"

1: welcome to line-editor
2: # made by suzuki-hoge
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> undo

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 
```

#### redo (r)
`[command-name]`

直前の`undo`を戻す

ただし、以下の場合は不正コマンドとする

+ `undo`直後でない場合

```
1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 1/append/"# made by suzuki-hoge"

1: welcome to line-editor
2: # made by suzuki-hoge
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> undo

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> redo

1: welcome to line-editor
2: # made by suzuki-hoge
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> 
```

#### execute (e)
`[line-range]/[command-name]/[regex-text]/[motion]`

`[line-range]`の行の`[regex-text]`に一致した行に対して`[motion]`を実行する

`[motion]`は`c`, `p`, `d`を任意数繋げて表現される

+ `c (capitalize)`: 行の先頭を大文字にする
+ `p (period)`: 行の末尾に`.`を付与する
+ `d (delete)`: 行を削除する
+ `cpd`の順番は問わない
+ `cpd`の間には任意数の`半角スペース`を含めることができる

```
1: welcome to line-editor
2: # made by suzuki-hoge
3: 
4: this work is coding practice with simple line editor creation
5: if you are interested in line editor, check out linux "ed"
6: 
7: good practice!

> % e/"^#"/d

1: welcome to line-editor
2: 
3: this work is coding practice with simple line editor creation
4: if you are interested in line editor, check out linux "ed"
5: 
6: good practice!

> 3, $/execute/"\w$"/c p

1: welcome to line-editor
2: 
3: This work is coding practice with simple line editor creation.
4: If you are interested in line editor, check out linux "ed".
5: 
6: good practice!

> 
```

#### write (w)
`[command-name]`

編集内容を保存して終了する

## 学習計画
+ 入出力やコマンド解析や各種コマンド実装を、それぞれ最低機能から設計と実装をする
+ 初めにある程度まとめて、小さくタスク出しを行い、最低限動くものを実現してから機能強化をするのが望ましい
  1. `ex) 起動してファイルの中身を表示し、コマンドを待ち受ける`
  1. `ex) [command-name] 前後の半角スペースは１つの場合のみ受け付ける`
  1. `ex) [line-range] は [start-line-number] のみ受け付ける`
  1. `ex) delete を実装する ( 1 delete )`
  1. `ex) 任意数の半角スペースでも受け付けられる様にする`
  1. `ex) (, [end-line-number]) を受け付けられる様にする`
  1. `ex) d でも受け付けられる様にする ( 1, $ 　 d )`
+ git を用い、小さく実装をしコミットをするのが望ましい
+ テストコードを書いて動作保証をすること
+ まとめて最後にテスト実装をせずに、小さく仕上げるコミットそれぞれでテストを実装する
+ 学習終了時に git のコミットと差分を見て、クラス分割の設計や要件と差分発生箇所をふりかえる
+ 長くとも 1 ヶ月かつ 30 ~ 40 時間程度に目標を定め、短期間で集中してできる範囲をやりきるのが望ましい
  + 大変な場合は自分で要件やコマンドを削ったりして、定めた時間に収まる様に自分でスコープ調整をする
  + ex) `半角スペースは１つに限る`, `不正コマンドは異常終了とする`, `undo / redo / execute を諦める`等
+ 学習終了時にふりかえりをし、経過時間 / うまくいった点 / うまくいかなかった点等を残す
+ タスクと見積もりも残すと良い
+ 半年〜１年のち、再学習をするのが望ましい
  + 自分のふりかえり内容に応じて要件を増減させてみたりしても良い
  + ex) `undo を何度でもできる様にする`, `Substitute を motion 化する`等
+ ふりかえりの後、定めた時間以上続けたり、すぐにやり直しても当然良い

## 学習観点
+ プロジェクト作成
+ 依存ライブラリのセットアップ
+ ファイル入出力
+ 文字列操作
+ 配列操作
+ 正規表現
+ パッケージ設計
+ クラス設計
+ 標準入出力
+ 動くものを作りきる
+ 小さく作り、組み合わせて要件を実現する
+ タスク出し、概算見積もり
+ やることとやらないことを決める
+ テストパターン設計
+ テストしやすい設計
+ 小さくテストする
