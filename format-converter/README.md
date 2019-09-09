# Format Converter
paiza や codewars の様な１クラスかつ最初からある程度使うメソッドの目安が付く様なお題とは別に、小中規模の要件を０から作り上げる経験も必要なので、そのお題を用意した。

仕様を本ページ上部に、学習の観点と計画を本ページ下部に記載する。

## 仕様
### 実行
```
$ cat comma-separated-sample.txt

order id,item name,item price,item quantity
1,sandwich,120,1
2,fried chicken, 160, 1
3,tea,120,2

$ format-converter comma-separated-sample.txt --src=c --dst=t

order-id:1	item-name:sandwich	item-price:120	item-quantity:1
order-id:2	item-name:fried chicken	item-price:160	item-quantity:1
order-id:3	item-name : tea	item-price : 120	item-quantity : 2
```

の様な形で実行する。

コマンド名は細かくは何でも良い。

### 基本仕様
+ `[path]`は変換元となるファイルパス
  + 第一引数であること
+ `--src`で変換元フォーマットを指定する
+ `--dst`で変換後フォーマットを指定する
+ `--src`と`--dst`の順番は逆でも動くこと
+ 変換結果は標準出力をする

### フォーマット３種
１回分の購入品の情報をそれぞれのフォーマットで示す。

`(c)`の部分が`--src`および`--dst`で指定する際に使う値となる

#### comma separated (c)
+ １行目をキー名、それ以降を値としてデータを記入する
+ キーは`全て小文字`で`半角スペース`で区切られる
+ `,`の前後の`半角スペース`は無視される
+ `空行`を認める

```
order id,item name,item price,item quantity

1,sandwich,120,1
2,fried chicken, 160, 1
3,tea,120,2
```

#### labeled tab separated (t)
+ `key:val`を`タブ`で区切りデータを記入する
+ キーは`全て小文字`で`-`で区切られる
+ キーの順番は全ての行で同じであること
+ `:`の前後の`半角スペース`は無視される
+ `空行`を認める

```
order-id:1	item-quantity:1	item-price:120	item-name:sandwich
order-id:2	item-quantity:1	item-price:160	item-name:fried chicken

order-id:3	item-quantity:2	item-price:120	item-name:tea
```

#### blocks (b)
+ `key=val`を`空行`で区切りデータを記入する
+ キーは`キャメルケース`である
+ キーの順番は全てのブロックで同じであること
+ `=`の前後の`半角スペース`は無視される
+ 連続する`空行`を認めない

```
orderId=1
itemName=sandwich
itemPrice=120
itemQuantity=1

orderId=2
itemName=fried chicken
itemPrice=160
itemQuantity=1

orderId=3
itemName=tea
itemPrice=120
itemQuantity=2
```

### 変換ルール
### バリデーション
+ コマンドライン引数およびファイル内容ともに、行わなくて良い
  + 入力は全て正しく行われるとする
  
## 学習計画
+ 各種パーサを小さく作り上げ、組み上げてパーサを作り上げる様に考えると良い
+ git を用い、小さく実装をしコミットをするのが望ましい
+ テストコードを書いて動作保証をすること
+ まとめて最後にテスト実装をせずに、小さく仕上げるコミットそれぞれでテストを実装する
+ 学習終了時に git のコミットと差分を見て、クラス分割の設計や追加要件と差分発生箇所をふりかえる
+ 長くとも 2 週間かつ 10 ~ 20 時間程度に目標を定め、要件を絞って動くものを作り上げるのを目指すのが望ましい
  + ex) `:`や`=`の前後の`半角スペース`はないものとする
  + ex) `comma separated`と`labeled tab separated`のみ対応する
  + ex) 全てのキーは`小文字でハイフン区切り`とする
+ 学習終了時にふりかえりをし、経過時間 / うまくいった点 / うまくいかなかった点等を残す
+ タスクと見積もり、および実績値も残すと良い
+ 半年〜１年のち、再学習をするのが望ましい
  + 自分のふりかえり内容に応じて要件を増減させてみたりしても良い
  + ex) コマンドの入力バリデーションをする
  + ex) ファイルの内容に不備があった場合にエラーを表示する
  + ex) `yaml`や`xml`の様なフォーマットにも対応する
+ ふりかえりの後、定めた時間以上続けたり、すぐにやり直しても当然良い

## 学習観点
+ プロジェクト作成
+ 依存ライブラリのセットアップ
+ ファイル入出力
+ コマンドライン引数
+ 文字列操作
+ 配列操作
+ パッケージ設計
+ 動くものを作りきる
+ テストパターン設計
+ テストしやすい設計
+ 小さくテストする
+ git
