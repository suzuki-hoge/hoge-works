package safe;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import static javaslang.API.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Card implements Comparable {
    public final Suit suit;
    public final Rank rank;

    public enum Suit {
        S, H, D, C
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Rank {
        public final int value;

        @Override
        public String toString() {
            return Match(value).of(
                    Case($(14), "A"),
                    Case($(13), "K"),
                    Case($(12), "Q"),
                    Case($(11), "J"),
                    Case($(), String::valueOf)
            );
        }
    }

    @Override
    public String toString() {
        return suit + "-" + rank;
    }

    @Override
    public int compareTo(Object o) {
        Card other = (Card) o;
        return rank.value == other.rank.value
                ? suit.compareTo(other.suit)
                : Integer.compare(rank.value, ((Card) o).rank.value);
    }
}
