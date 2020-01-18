package safe

import org.scalatest.FunSuite

class FlowTest extends FunSuite {
  test("apply") {
    assert(
      Flow("small").startsWith("スモールプラン")
    )
    assert(
      Flow("big") == "不正なプラン名です。"
    )
  }
}
