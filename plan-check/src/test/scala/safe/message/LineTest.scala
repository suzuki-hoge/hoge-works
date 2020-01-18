package safe.message

import org.scalatest.FunSuite
import safe.domain.{Basic, Plans, Small}

class LineTest extends FunSuite {
  test("show") {
    assert(
      Line(Plans.get(Small, Basic).right.get).show == "ベーシック会員 の方は スモールプラン を 毎月 3,000 円 で 1 GB ご利用できます。"
    )
  }
}
