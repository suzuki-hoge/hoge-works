package safe.message

import org.scalatest.FunSuite
import safe.domain.{Plans, Small}

class LineTest extends FunSuite {
  test("show") {
    assert(
      Line(Plans.get(Small)).show == "スモールプラン は 毎月 3,000 円 で 1 GB ご利用できます。"
    )
  }
}
