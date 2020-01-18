package safe;

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.control.Option;
import lombok.val;
import safe.Card.Rank;

import static javaslang.control.Option.none;
import static javaslang.control.Option.some;
import static safe.Hand.Name.*;

public interface Hand {
    Option<Tuple2<Name, Card>> judge(List<Card> cards);

    // hands

    enum Name {
        HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalStraightFlush
    }

    static Option<Tuple2<Name, Card>> match(List<Card> cards) {
        List<Hand> hands = List.of(
                Hand::royalStraightFlush, Hand::straightFlush,
                Hand::fourOfAKind, Hand::fullHouse, Hand::straight,
                Hand::threeOfAKind, Hand::twoPair, Hand::onePair
        );

        return hands.flatMap(f -> f.judge(cards)).headOption();
    }

    // hand defs

    static Option<Tuple2<Name, Card>> onePair(List<Card> cards) {
        val kinds = nOfKind(2, cards);

        return kinds.length() == 1 ? found(OnePair, kinds.head()) : none();
    }

    static Option<Tuple2<Name, Card>> twoPair(List<Card> cards) {
        val kinds = nOfKind(2, cards);

        return kinds.length() == 2 ? found(TwoPair, kinds.head()) : none();
    }

    static Option<Tuple2<Name, Card>> threeOfAKind(List<Card> cards) {
        return nOfKind(3, cards).headOption().flatMap(card -> found(ThreeOfAKind, card));
    }

    static Option<Tuple2<Name, Card>> straight(List<Card> cards) {
        List<Rank> ranks = cards.map(card -> card.rank);
        int min = ranks.head().value;
        List<Rank> exp = List.range(min, min + 5).map(Rank::new);

        return ranks.equals(exp)
                ? found(Straight, cards.last())
                : none();
    }

    static Option<Tuple2<Name, Card>> flush(List<Card> cards) {
        return cards.forAll(card -> cards.head().suit == card.suit)
                ? found(Flush, cards.last())
                : none();
    }

    static Option<Tuple2<Name, Card>> fullHouse(List<Card> cards) {
        return nOfKind(2, cards).nonEmpty() && nOfKind(3, cards).nonEmpty()
                ? found(FullHouse, nOfKind(3, cards).last())
                : none();
    }

    static Option<Tuple2<Name, Card>> fourOfAKind(List<Card> cards) {
        return nOfKind(4, cards).headOption().flatMap(card -> found(FourOfAKind, card));
    }

    static Option<Tuple2<Name, Card>> straightFlush(List<Card> cards) {
        return straight(cards).isDefined() && flush(cards).isDefined()
                ? found(StraightFlush, cards.last())
                : none();
    }

    static Option<Tuple2<Name, Card>> royalStraightFlush(List<Card> cards) {
        return straightFlush(cards).isDefined() && cards.head().rank.value == 10
                ? found(RoyalStraightFlush, cards.last())
                : none();
    }

    // utils

    static List<Card> nOfKind(int n, List<Card> cards) {
        return cards
                .groupBy(card -> card.rank.value)
                .filter(tup -> tup._2.length() == n)
                .map(tup -> tup._2.sorted().head())
                .sorted().reverse().toList();
    }

    static Option<Tuple2<Name, Card>> found(Name name, Card card) {
        return some(Tuple.of(name, card));
    }
}
