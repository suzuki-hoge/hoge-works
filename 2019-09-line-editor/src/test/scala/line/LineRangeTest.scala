package line

import org.scalatest.FunSuite
import util.Helpers._

class LineRangeTest extends FunSuite {
  test("contains") {
    val sut = $$(2, 4)

    assert(
      sut.contains($(2))
    )
    assert(
      sut.contains($(3))
    )
    assert(
      sut.contains($(4))
    )

    assert(
      !sut.contains($(1))
    )
    assert(
      !sut.contains($(5))
    )
  }

  private val last = 5

  test("parse") {
    assert(
      LineRange.parse("1", last).contains($$(1, 1))
    )
    assert(
      LineRange.parse("1,2", last).contains($$(1, 2))
    )
    assert(
      LineRange.parse("1  ,  $", last).contains($$(1, last))
    )
    assert(
      LineRange.parse("$", last).contains($$(last, last))
    )
    assert(
      LineRange.parse("%", last).contains($$(1, last))
    )
  }

  test("parse (no match)") {
    assert(
      LineRange.parse("1 $", last).isEmpty
    )
    assert(
      LineRange.parse("0,2", last).isEmpty
    )
    assert(
      LineRange.parse("2,6", last).isEmpty
    )
    assert(
      LineRange.parse("1,%", last).isEmpty
    )
  }
}
