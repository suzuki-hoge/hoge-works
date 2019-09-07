package command

import line.Text
import org.scalatest.FunSuite
import util.Helpers._

class ExecuteTest extends FunSuite {
  private val lines = $$$("foo", "bar", "pon")
  private val sut1 = Execute($$(1, 2), Text("^p.*"), Motions(Seq(Capitalize, Period)))
  private val sut2 = Execute($$(1, 3), Text("^p.*"), Motions(Seq(Capitalize, Period)))

  test("execute") {
    assert(
      sut1.execute(lines) == $$$("foo", "bar", "pon")
    )
    assert(
      sut2.execute(lines) == $$$("foo", "bar", "Pon.")
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
      Execute.parse("1, $/execute/^p.*/c p", lines.$).contains(sut2)
    )
  }

  test("parse (no match)") {
    assert(
      Execute.parse("2, $/execute/^p.*/", lines.$).isEmpty
    )
  }
}
