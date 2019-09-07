package command

import org.scalatest.FunSuite
import util.Helpers._

class DeleteTest extends FunSuite {
  private val lines = $$$("foo", "bar", "pon")
  private val sut1 = Delete($$(1, 2))
  private val sut2 = Delete($$(2, 3))
  private val sut3 = Delete($$(1, 3))

  test("execute") {
    assert(
      sut1.execute(lines) == $$$("pon")
    )
    assert(
      sut2.execute(lines) == $$$("foo")
    )
    assert(
      sut3.execute(lines) == $$$()
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
      Delete.parse("2 , $ / d", lines.$).contains(sut2)
    )
  }

  test("parse (no match)") {
    assert(
      Delete.parse("1,2", lines.$).isEmpty
    )
    assert(
      Delete.parse("delete", lines.$).isEmpty
    )
  }
}
