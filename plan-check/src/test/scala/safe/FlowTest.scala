package safe

import org.scalatest.FunSuite

class FlowTest extends FunSuite {
  test("apply") {
    assert(
      Flow("small", "basic").startsWith("ベーシック")
    )
    assert(
      Flow("big", "basic") == "不正なプラン名です。"
    )
    assert(
      Flow("normal", "free") == "不正なタイプです。"
    )
    assert(
      Flow("mega", "basic") == "不正な組み合わせです。"
    )
    assert(
      Flow("mega", "premium").startsWith("プレミアム")
    )
  }
}
