package safe

import org.scalatest.FunSuite

class FlowTest extends FunSuite {
  test("apply") {
    assert(
      Flow("small", "basic", "normal").contains("ノーマル")
    )
    assert(
      Flow("big", "basic", "normal") == "不正なプラン名です。"
    )
    assert(
      Flow("normal", "free", "normal") == "不正なタイプです。"
    )
    assert(
      Flow("mega", "basic", "normal") == "不正な組み合わせです。"
    )
    assert(
      Flow("normal", "basic", "small") == "スモールプランへの変更はできません。"
    )
    assert(
      Flow("normal", "basic", "normal") == "同じプランへの変更はできません。"
    )
    assert(
      Flow("normal", "basic", "mega") == "メガプランはプレミアム会員専用です。"
    )
    assert(
      Flow("normal", "premium", "mega").contains("変更しました")
    )
  }
}
