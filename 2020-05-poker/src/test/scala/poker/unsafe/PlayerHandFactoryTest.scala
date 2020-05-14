package poker.unsafe

import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table

class PlayerHandFactoryTest extends FunSuite {

  forAll(Table(
    //@formatter:off
    ("ws",                       "exp"),
    ("S-2  H-3  D-4  C-5  S-6",  Right(())),
    ("S-10 H-J  D-Q  C-K  S-A",  Right(())),
    ("S-1  H-11 D-12 C-13 S-14", Left("no number format, no number format, no number format, no number format, no number format")),
    ("s-10 h-J  d-Q  c-K  s-A",  Left("no suit format, no suit format, no suit format, no suit format, no suit format")),
    ("S-10 H-J  D-Q  C-K",       Left("must be 5 cards")),
    ("S-10 H-J  D-Q  C-K  S-10", Left("dup cards"))
    //@formatter:on
  )) { (ws: String, exp: Either[String, Unit]) =>
    assert(
      PlayerHandFactory.fromString(ws).map(_ => ()) == exp
    )
  }
}
