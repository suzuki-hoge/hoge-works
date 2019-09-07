package command

import org.scalatest.FunSuite

class UndoTest extends FunSuite {
  val last = 5

  test("parse") {
    assert(
      Undo.parse("undo", last).contains(Undo)
    )
    assert(
      Undo.parse("u", last).contains(Undo)
    )
  }

  test("parse (no match)") {
    assert(
      Undo.parse("1/undo", last).isEmpty
    )
  }
}
