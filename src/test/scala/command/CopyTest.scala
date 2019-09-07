package command

import org.scalatest.FunSuite
import util.Helpers._

class CopyTest extends FunSuite {
  private val lines = $$$("foo", "bar", "pon")
  private val sut1 = Copy($$(1, 2), $(2))
  private val sut2 = Copy($$(2, 3), $(3))
  private val sut3 = Copy($$(1, 3), $(2))

  test("execute") {
    assert(
      sut1.execute(lines) == $$$("foo", "bar", "foo", "bar", "pon")
    )
    assert(
      sut2.execute(lines) == $$$("foo", "bar", "pon", "bar", "pon")
    )
    assert(
      sut3.execute(lines) == $$$("foo", "bar", "foo", "bar", "pon", "pon")
    )
  }

  test("undo") {
    val executed1 = sut1.execute(lines)
    val executed2 = sut2.execute(lines)
    val executed3 = sut3.execute(lines)

    assert(
      sut1.undo(lines).get.execute(executed1) == lines
    )
    assert(
      sut2.undo(lines).get.execute(executed2) == lines
    )
    assert(
      sut3.undo(lines).get.execute(executed3) == lines
    )
  }

  test("parse") {
    assert(
      Copy.parse("1,2/copy/2", lines.$).contains(sut1)
    )
  }

  test("parse (no match)") {
    assert(
      Copy.parse("1,2/c/3,$", lines.$).isEmpty
    )
    assert(
      Copy.parse("1/copy", lines.$).isEmpty
    )
    assert(
      Copy.parse("1/copy/4", lines.$).isEmpty
    )
    assert(
      Copy.parse("0, 2/copy/3", lines.$).isEmpty
    )
    assert(
      Copy.parse("1, 4/copy/2", lines.$).isEmpty
    )
  }
}
