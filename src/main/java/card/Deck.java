package card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> bill;

    {
        bill = Arrays.stream(Suit.values()).filter(s -> s != Suit.JOKER).flatMap(s -> IntStream.rangeClosed(1, 13).mapToObj(i -> new Card(s, i))).collect(Collectors.toList());
        bill.add(new Card(Suit.JOKER, 0));
        Collections.shuffle(bill);
    }

    public Card draw() {
        Card card = bill.get(0);
        bill.remove(0);
        return card;
    }
}
