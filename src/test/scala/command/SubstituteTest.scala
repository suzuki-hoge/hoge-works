package command

import line.Text
import org.scalatest.FunSuite
import util.Helpers._

class SubstituteTest extends FunSuite {
  private val lines = $$$("foo", "bar", "pon")
  private val sut1 = Substitute($$(1, 2), Text("r"), Text("a"))
  private val sut2 = Substitute($$(2, 3), Text("r"), Text("a"))

  test("execute") {
    assert(
      sut1.execute(lines) == $$$("foo", "baa", "pon")
    )
    assert(
      sut2.execute(lines) == $$$("foo", "baa", "pon")
    )
  }

  test("undo") {
    val executed1 = sut1.execute(lines)
    val executed2 = sut2.execute(lines)

    assert(
      sut1.undo(lines).execute(executed1) == lines
    )
    assert(
      sut2.undo(lines).execute(executed2) == lines
    )
  }

  test("parse") {
    assert(
      Substitute.parse("2,$/substitute/r/a", lines.$).contains(sut2)
    )
  }

  test("parse (no match)") {
    assert(
      Substitute.parse("1,2/s/r a", lines.$).isEmpty
    )
  }
}
