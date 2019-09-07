package command

import line.Body
import org.scalatest.FunSuite
import util.Helpers._

class AppendTest extends FunSuite {
  private val lines = $$$("foo", "bar")
  private val sut = Append($(1), Body.of("new line."))

  test("execute") {
    assert(
      sut.execute(lines) == $$$("foo", "new line.", "bar")
    )
  }

  test("undo") {
    val executed = sut.execute(lines)

    assert(
      sut.undo(lines).get.execute(executed) == lines
    )
  }

  test("parse") {
    assert(
      Append.parse("1/append/new line.", lines.$).contains(sut)
    )
  }

  test("parse (no match)") {
    assert(
      Append.parse("1,2/append/new line.", lines.$).isEmpty
    )
    assert(
      Append.parse("1/append", lines.$).isEmpty
    )
  }
}
