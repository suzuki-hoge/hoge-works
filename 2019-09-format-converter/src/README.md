# implementation
お題の詳細部分を検証するために実装したコード

解答例ではない

#### まぁ良い点
+ `Key`は内部構造的にはバラして持っていて、dst parse する時に組み上げることにした点
+ `Data` → `Record` → `Element` → `Key / Value`の構成は扱うのは面倒だけど`List<List<List<Tuple>>>`より全然安全なので良い
+ 9 割は最初に書いたクラス図通り実装できた
  + 差分が出たのはだいたい`boot`のあたり
  + `boot`は副作用が多いので最後に考えるつもりだったので妥当
+ テストもまぁまぁよく書けた

#### ちょっと気になる点
+ ３つフォーマットを用意と共通化やパッケージ依存の課題が見えづらいかと思ったけど、途中で飽きてしまったので、２つでも良かったかもしれない
+ `blocks`が少し難しいかもしれない
+ 思ったより時間がかかった
  + クラス図におよそ 1h, 実装におよそ 3h 要した
  + 詳細内訳はコミットメッセージ参照
    + [write parsers.](https://github.com/suzuki-hoge/hoge-work/commit/935eba067a8610d6bd3aa7c2829e652067ea2fcb)
    + [write boot package.](https://github.com/suzuki-hoge/hoge-work/commit/39d60990381aa59df9c062b5452445cd582eac20)
  + 要因は java が書き辛すぎること、のみ
    + `List`に`head`, `tail`, `init`, `last`, `zip`等がない
    + `List`の`reduce`が使いづらい
    + 逐一出てくる`配列`と`.stream()`が邪魔
    + `String`の`case`変換が使いづらい
    + 等々々々
