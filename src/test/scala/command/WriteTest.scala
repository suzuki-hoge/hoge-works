package command

import org.scalatest.FunSuite

class WriteTest extends FunSuite {
  val last = 5

  test("parse") {
    assert(
      Write.parse("write", last).contains(Write)
    )
    assert(
      Write.parse("w", last).contains(Write)
    )
  }

  test("parse (no match)") {
    assert(
      Write.parse("1/write", last).isEmpty
    )
  }
}
