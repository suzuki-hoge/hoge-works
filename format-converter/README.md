# Format Converter
軽量な行指向のデータの相互変換を実装するお題。

## 仕様
### 実行
```
$ cat comma-separated-sample.txt

order id,item name,item price,item quantity
1,sandwich,120,1
2,fried chicken, 160, 1
3,tea,120,2

$ format-converter comma-separated-sample.txt --src c --dst t

order-id:1	item-name:sandwich	item-price:120	item-quantity:1
order-id:2	item-name:fried chicken	item-price:160	item-quantity:1
order-id:3	item-name : tea	item-price : 120	item-quantity : 2
```

の様な形で実行する

コマンド名は細かくは何でも良い

### 基本仕様
+ `[path]`は変換元となるファイルパス
+ 変換結果は標準出力をする
+ `--src`で変換元フォーマットを指定する
+ `--dst`で変換後フォーマットを指定する

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

## 学習観点
+ コマンドライン引数
+ 文字列操作
+ 配列操作
+ レイヤー設計
+ 共通部品と専用部品の分離
+ パッケージの相互依存の考慮