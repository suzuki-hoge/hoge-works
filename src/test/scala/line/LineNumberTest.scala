package line

import org.scalatest.FunSuite
import util.Helpers._

class LineNumberTest extends FunSuite {
  test("condition") {
    assert(
      $(1) == $(1)
    )
    assert(
      !($(1) == $(2))
    )
    assert(
      $(1) <= $(1)
    )
    assert(
      $(1) <= $(2)
    )
    assert(
      !($(2) <= $(1))
    )
    assert(
      $(1) >= $(1)
    )
    assert(
      $(2) >= $(1)
    )
    assert(
      !($(1) >= $(2))
    )
    assert(
      !($(1) < $(1))
    )
    assert(
      $(1) < $(2)
    )
    assert(
      !($(1) > $(1))
    )
    assert(
      $(2) > $(1)
    )
  }

  test("operation") {
    assert(
      $(1) + 1 == $(2)
    )
    assert(
      $(2) - 1 == $(1)
    )
  }

  private val last = 5

  test("parse") {
    assert(
      LineNumber.parse("1", last).contains($(1))
    )
    assert(
      LineNumber.parse("5", last).contains($(last))
    )
    assert(
      LineNumber.parse("$", last).contains($(last))
    )
  }

  test("parse (no match)") {
    assert(
      LineNumber.parse("", last).isEmpty
    )
    assert(
      LineNumber.parse("1,2", last).isEmpty
    )
    assert(
      LineNumber.parse("%", last).isEmpty
    )
    assert(
      LineNumber.parse("0", last).isEmpty
    )
    assert(
      LineNumber.parse("6", last).isEmpty
    )
  }
}
