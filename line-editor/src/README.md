# implementation
お題の詳細部分を検証するために実装したコード

解答例ではない

#### まぁ良い点
+ パッケージの上下関係がシンプル
  + `editor` → `command` → `line`
+ `line`, `command`の順で実装をし、先に作った小さな処理の組み合わせでほぼ実装しきれた
+ 9 割は最初に書いたクラス図通り実装できた
  + 差分が出たのはだいたい`Command`の継承関係と`Editor`のあたり
  + scala の`object`の`extends`や`trait`の`match-case`の細かい扱いをわかってなかった
  + `Editor`は副作用が多いので最後に考えるつもりだったので妥当
+ `Editor`の設計は最後に回したけど、ほぼ想定した組み上げ方で仕上がった
+ やはり演算子が自分で書ける言語は`.getValue`が消えるのですっきりして良い
+ 行番号表示を出力処理ではなく変換処理として実装してテストした点は関数的で良かった
+ パース処理の`for`はまぁまぁ収まりが良かった
+ `Bool`に`implicit`でメソッドを生やしたのは面白かった
  + `identity`ともはまるし
  + 実装の置き場は適当だし、そもそも実案件でやるかは別だけど
+ テストもまぁまぁよく書けた

#### ちょっと気になる点
+ `LineCommand.parse`の部分が子方向に依存しているのを解決したかった
+ `Text`と`Body`はもう少し丁寧に設計すればよかった
+ 終盤で`Body`を class から trait にして`NonVisibleBody`とかを作った
  + 最初からなんとなく必要になる気がしていたけどサボってた、ちょっとリファクタが辛かった
  + `Body`まわりはもう少し綺麗にできたかもしれない
    + `flatMap`でフィルターしてる処理が何箇所か...
+ 思ったよりリスト処理が主題にならなかった
  + ので途中で０ベースを仕上げるお題に方針転換したので、その点では良かった
+ 思ったより時間がかかった
  + クラス図におよそ 2h, 実装におよそ 8h 要した
  + 詳細内訳はコミットメッセージ参照
    + [implement line package and command package.](https://github.com/suzuki-hoge/hoge-work/commit/1a0a8f1050cffe83572865519e91896c9f7ce540)
    + [implement editor package.](https://github.com/suzuki-hoge/hoge-work/commit/5bf08f4a9fe37bdf133ca479c2f2782634cc0e50)
