package poker.safe

import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table
import poker.unsafe.{CardFactory, PlayerHandFactory}

class PlayerHandTest extends FunSuite {

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-3 H-3 D-4 C-5 S-6", Some(OnePair, $("S-3"))),
    ("D-3 H-3 D-4 C-5 S-6", Some(OnePair, $("H-3"))),
    ("S-2 H-3 D-4 C-5 S-6", None),
    ("S-3 H-3 D-3 C-5 S-6", None),
    ("S-3 H-3 D-4 C-6 S-6", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).onePair == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-3 H-3 D-4 C-4 S-6", Some(TwoPair, $("D-4"))),
    ("S-3 H-3 D-4 C-5 S-6", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).twoPair == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-3 H-3 D-3 C-4 S-6", Some(ThreeOfAKind, $("S-3"))),
    ("S-3 H-3 D-3 C-3 S-6", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).threeOfAKind == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-2 H-3 D-4 C-5 S-6",  Some(Straight, $("S-6"))),
    ("S-A S-K S-Q S-J S-10", Some(Straight, $("S-A"))),
    ("S-3 H-4 D-5 C-6 S-8",  None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).straight == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-2 S-A S-6 S-8 S-K",  Some(Flush, $("S-A"))),
    ("S-2 D-A S-6 S-8 S-K",  None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).flush == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-2 D-2 S-3 D-3 H-3",  Some(FullHouse, $("S-3"))),
    ("S-2 D-2 S-3 D-3 H-4",  None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).fullHouse == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-3 H-3 D-3 C-3 S-6", Some(FourOfAKind, $("S-3"))),
    ("S-3 H-3 D-3 C-5 S-6", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).fourOfAKind == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-2 S-3 S-4 S-5 S-6", Some(StraightFlush, $("S-6"))),
    ("S-2 S-3 S-4 S-5 D-6", None),
    ("S-2 S-3 S-4 S-5 S-7", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).straightFlush == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-10 S-J S-Q S-K S-A", Some(RoyalStraightFlush, $("S-A"))),
    ("S-2  S-3 S-4 S-5 S-6", None)
    //@formatter:on
  )) { (ws: String, exp: Option[(Hand, Card)]) => assert($$(ws).royalStraightFlush == exp) }

  forAll(Table(
    //@formatter:off
    ("ws",                  "exp"),
    ("S-10 S-J S-Q S-K S-A", (RoyalStraightFlush, $("S-A"))),
    ("S-3  H-3 D-3 C-3 S-6", (FourOfAKind,        $("S-3"))),
    ("S-2  D-2 S-3 D-3 H-3", (FullHouse,          $("S-3"))),
    ("S-3  H-3 D-4 C-4 S-6", (TwoPair,            $("D-4"))),
    ("S-2  S-3 S-4 S-5 H-7", (HighCard,           $("H-7")))
    //@formatter:on
  )) { (ws: String, exp: (Hand, Card)) => assert($$(ws).judge == exp) }

  private def $$(ws: String): PlayerHand = PlayerHandFactory.fromString(ws).right.get

  private def $(w: String): Card = CardFactory.fromString(w).right.get
}
