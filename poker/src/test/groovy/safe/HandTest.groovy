package safe

import javaslang.collection.List
import spock.lang.Specification
import spock.lang.Unroll
import unsafe.Parser

import static javaslang.control.Option.none
import static safe.Hand.Name.*
import static safe.Hand.found

@Unroll
class HandTest extends Specification {
    private static Card $(String s) {
        Parser.toCard(s)
    }

    private static List<Card> $$(String s) {
        Parser.toCards(s)
    }

    def onePair() {
        expect:
        Hand.onePair(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 S-6') || none()
        $$('S-2 S-3 S-4 S-5 H-5') || found(OnePair, $("S-5"))
        $$('S-2 S-3 S-4 H-4 D-4') || none()
    }

    def twoPair() {
        expect:
        Hand.twoPair(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 H-5') || none()
        $$('S-2 S-3 H-3 S-4 H-4') || found(TwoPair, $("S-4"))
        $$('S-2 H-2 S-3 H-3 D-3') || none()
    }

    def threeOfAKind() {
        expect:
        Hand.threeOfAKind(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 S-6') || none()
        $$('S-2 S-3 S-4 H-4 D-4') || found(ThreeOfAKind, $("S-4"))
        $$('S-2 S-3 H-3 D-3 C-3') || none()
    }

    def straight() {
        expect:
        Hand.straight(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 H-5') || none()
        $$('S-2 S-3 S-4 S-5 S-6') || found(Straight, $("S-6"))
    }

    def flush() {
        expect:
        Hand.flush(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 H-6') || none()
        $$('S-2 S-3 S-4 S-5 S-6') || found(Flush, $("S-6"))
    }

    def fullHouse() {
        expect:
        Hand.fullHouse(cards) == exp

        where:
        cards                     || exp
        $$('S-2 H-2 S-3 H-3 S-4') || none()
        $$('S-2 H-2 S-3 H-3 D-3') || found(FullHouse, $("S-3"))
    }

    def fourOfAKind() {
        expect:
        Hand.fourOfAKind(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 H-3 D-3 S-4') || none()
        $$('S-2 S-3 H-3 D-3 C-3') || found(FourOfAKind, $("S-3"))
    }

    def straightFlush() {
        expect:
        Hand.straightFlush(cards) == exp

        where:
        cards                     || exp
        $$('S-2 S-3 S-4 S-5 H-6') || none()
        $$('S-2 S-3 S-4 S-5 S-6') || found(StraightFlush, $("S-6"))
    }

    def royalStraightFlush() {
        expect:
        Hand.royalStraightFlush(cards) == exp

        where:
        cards                      || exp
        $$('S-10 S-J S-Q S-K S-A') || found(RoyalStraightFlush, $("S-A"))
    }

    def match() {
        expect:
        Hand.match(cards) == exp

        where:
        cards                      || exp
        $$('S-10 S-J S-Q S-K S-A') || found(RoyalStraightFlush, $("S-A"))
        $$('S-2 H-2 S-3 H-3 D-3')  || found(FullHouse, $("S-3"))
        $$('S-2 S-3 S-4 S-5 H-7')  || none()
    }
}
