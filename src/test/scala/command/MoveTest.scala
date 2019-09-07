package command

import org.scalatest.FunSuite
import util.Helpers._

class MoveTest extends FunSuite {
  private val lines = $$$("foo", "bar", "pon", "kaz")
  private val sut1 = Move($$(1, 2), $(3))
  private val sut2 = Move($$(3, 4), $(1))

  test("execute") {
    assert(
      sut1.execute(lines) == $$$("pon", "foo", "bar", "kaz")
    )
    assert(
      sut2.execute(lines) == $$$("foo", "pon", "kaz", "bar")
    )
  }

  test("undo") {
    val executed1 = sut1.execute(lines)
    val executed2 = sut2.execute(lines)

    assert(
      sut1.undo(lines).get.execute(executed1) == lines
    )
    assert(
      sut2.undo(lines).get.execute(executed2) == lines
    )
  }

  test("parse") {
    assert(
      Move.parse("1,2/move/3", lines.$).contains(sut1)
    )
    assert(
      Move.parse("3 , 4 / m / 1", lines.$).contains(sut2)
    )
  }

  test("parse (no match)") {
    assert(
      Move.parse("1,3/m/1", lines.$).isEmpty
    )
    assert(
      Move.parse("1,3/m/2", lines.$).isEmpty
    )
    assert(
      Move.parse("1,3/m/3", lines.$).isEmpty
    )
  }
}
