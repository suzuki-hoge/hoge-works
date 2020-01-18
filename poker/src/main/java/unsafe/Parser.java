package unsafe;

import javaslang.collection.List;
import safe.Card;

import static javaslang.API.*;
import static safe.Card.Rank;
import static safe.Card.Suit.*;

public class Parser {
    public static List<Card> toCards(String s) {
        return List.of(s.split(" ")).map(Parser::toCard).sorted();
    }

    public static Card toCard(String s) {
        return new Card(
                Match(s.split("-")[0]).of(
                        Case($("S"), S),
                        Case($("H"), H),
                        Case($("D"), D),
                        Case($("C"), C)
                ),
                new Rank(
                        s.split("-")[1].equals("J") ? 11 :
                                Match(s.split("-")[1]).of(
                                        Case($("A"), 14),
                                        Case($("K"), 13),
                                        Case($("Q"), 12),
                                        Case($("J"), 11),
                                        Case($(), Integer::valueOf)
                                )

                )
        );
    }
}
