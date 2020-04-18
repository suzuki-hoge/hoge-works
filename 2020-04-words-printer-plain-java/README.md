クラス大好きな感じでやった

+ 移譲主体
+ `Parsed`はちょっと遊んでるにしても、それ以外はある種真っ当
  + 雑な`Either`
+ 差分は主に３点、どっちも言語都合
  + まともな`reduce`がなかったので`Converter#and`を作った
  + まともな`flatMap`がなかったので`Optional`を`Stream`にし変えた
  + `Parsed`の`error`は`List`にして貯められる様にしたかったけど、めんどくさくなったので変えた
+ `Converter`, `Formatter`で`Words`受けるなら、そも static にしたい気持ち
+ package private 多め
+ 初 jig
  + package private とか implementation とか綺麗に見る方法、あるのかな？
+ safe は失敗とは切り離されている
+ non empty list までやろうと思ったけど、言語があれすぎて嫌になったので放置

この言語は肌に合わない

