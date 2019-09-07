package line

import line.Type.{F, SBF}
import org.scalatest.FunSuite
import util.Helpers._

class LinesTest extends FunSuite {
  private val sut = $$$("foo", "bar", "pon")
  private val convert: F = s => s + "!"
  private val append: SBF = b => Seq(b, Body.of("new line."))
  private val delete: SBF = _ => Seq()

  test("sequence with (empty body)") {
    assert(
      Lines.sequenceWith(Seq()) == Lines(Seq(Line($(0), NonVisibleBody), Line($(1), EmptyBody)))
    )
  }

  test("number conversion") {
    assert(
      sut.map(l => l.mapIf($(1), convert)) == $$$("foo!", "bar", "pon")
    )
  }

  test("range conversion") {
    assert(
      sut.map(l => l.mapIf($$(1, 3), convert)) == $$$("foo!", "bar!", "pon!")
    )
  }

  test("number addition") {
    assert(
      sut.sMap(l => l.sMapIf($(1), append)) == $$$("foo", "new line.", "bar", "pon")
    )
  }

  test("number deletion") {
    assert(
      sut.sMap(l => l.sMapIf($(1), delete)) == $$$("bar", "pon")
    )
  }

  test("range addition") {
    assert(
      sut.sMap(l => l.sMapIf($$(1, 3), append)) == $$$("foo", "new line.", "bar", "new line.", "pon", "new line.")
    )
  }

  test("range deletion") {
    assert(
      sut.sMap(l => l.sMapIf($$(1, 3), delete)) == $$$()
    )
  }

  test("yank") {
    assert(
      sut.yank($$(1, 2)) == Seq(Body.of("foo"), Body.of("bar"))
    )
  }

  test("yank (no match)") {
    assert(
      sut.yank($$(4, 5)) == Seq()
    )
  }

  test("display") {
    assert(
      sut.display == Seq("1: foo", "2: bar", "3: pon")
    )
  }
}
