package poker.safe

import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table
import poker.unsafe.CardFactory

class BattleTest extends FunSuite {

  forAll(Table(
    //@formatter:off
    ("ws",                                      "exp"),
    ("S-2 H-J S-Q D-J C-2 S-3 S-4 H-3 H-4 D-5", Right("first hand"))
    //@formatter:on
  )) { (ws: String, exp: Either[String, String]) => assert($$(ws) == exp) }

  private def $$(ws: String): Either[String, String] = Battle.apply(ws)

  private def $(w: String): Card = CardFactory.fromString(w).right.get
}
