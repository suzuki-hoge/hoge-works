package safe.domain

import org.scalatest.FunSuite

class PlanTest extends FunSuite {
  test("change") {
    assert(
      Plans.get(Normal, Basic).right.get.change(Small) == Left("small is not allowed")
    )
    assert(
      Plans.get(Normal, Basic).right.get.change(Normal) == Left("same plan")
    )
    assert(
      Plans.get(Normal, Basic).right.get.change(Mega) == Left("basic mega is not allowed")
    )
    assert(
      Plans.get(Normal, Premium).right.get.change(Mega) == Plans.get(Mega, Premium)
    )
  }
}
