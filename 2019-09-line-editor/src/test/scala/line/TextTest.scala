package line

import org.scalatest.FunSuite

class TextTest extends FunSuite {
  test("parse") {
    assert(
      Text.parse("new line.").contains(Text("new line."))
    )
    assert(
      Text.parse("").contains(Text(""))
    )
  }

  test("parse (no match)") {
    assert(
      Text.parse("new/line").isEmpty
    )
  }
}
