package line

import line.Type.{F, SBF}
import org.scalatest.FunSuite
import util.Helpers._

class LineTest extends FunSuite {
  private val sut = Line($(1), Body.of("foo"))
  private val convert: F = s => s + "!"
  private val append: SBF = b => Seq(b, Body.of("new line."))
  private val delete: SBF = _ => Seq()

  test("range conversion") {
    assert(
      sut.mapIf($$(1, 2), convert) == Body.of("foo!")
    )
  }

  test("range conversion (no match)") {
    assert(
      sut.mapIf($$(2, 3), convert) == Body.of("foo")
    )
  }

  test("number conversion") {
    assert(
      sut.mapIf($(1), convert) == Body.of("foo!")
    )
  }

  test("number conversion (no match)") {
    assert(
      sut.mapIf($(2), convert) == Body.of("foo")
    )
  }

  test("range addition") {
    assert(
      sut.sMapIf($$(1, 2), append) == Seq(Body.of("foo"), Body.of("new line."))
    )
  }

  test("range addition (no match)") {
    assert(
      sut.sMapIf($$(2, 3), append) == Seq(Body.of("foo"))
    )
  }

  test("range deletion") {
    assert(
      sut.sMapIf($$(1, 2), delete) == Seq()
    )
  }

  test("number addition") {
    assert(
      sut.sMapIf($(1), append) == Seq(Body.of("foo"), Body.of("new line."))
    )
  }

  test("number addition (no match)") {
    assert(
      sut.sMapIf($(2), append) == Seq(Body.of("foo"))
    )
  }

  test("number deletion") {
    assert(
      sut.sMapIf($(1), delete) == Seq()
    )
  }

  test("addition or deletion (addition)") {
    assert(
      sut.sMapIfOr($(1), append, $$(2, 2), delete) == Seq(Body.of("foo"), Body.of("new line."))
    )
  }

  test("addition or deletion (deletion)") {
    assert(
      sut.sMapIfOr($(2), append, $$(1, 1), delete) == Seq()
    )
  }

  test("yank") {
    assert(
      sut.yank($$(1, 2)).contains(Body.of("foo"))
    )
    assert(
      sut.yank($$(2, 3)).isEmpty
    )
  }

  test("display") {
    assert(
      sut.display.contains("1: foo")
    )
    assert(
      Line($(1), Body.nonVisible).display.isEmpty
    )
    assert(
      Line($(1), Body.empty).display.contains("1: ")
    )
  }
}
