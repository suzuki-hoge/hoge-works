package line

import line.Type.{F, SBF}
import org.scalatest.FunSuite

class BodyTest extends FunSuite {
  test("convert") {
    val f: F = s => s + "!"

    assert(
      Body.of("foo").map(f) == Body.of("foo!")
    )
  }

  test("addition") {
    val sbf: SBF = b => Seq(b, Body.of("new line."))

    assert(
      Body.of("foo").sMap(sbf) == Seq(Body.of("foo"), Body.of("new line."))
    )
  }

  test("deletion") {
    val sbf: SBF = _ => Seq()

    assert(
      Body.of("foo").sMap(sbf) == Seq()
    )
  }
}
