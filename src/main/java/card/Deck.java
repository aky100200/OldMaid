package card;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> bill;
    @Getter
    private final int maxSize;

    {
        bill = Arrays.stream(Suit.values()).filter(s -> s != Suit.JOKER).flatMap(s -> IntStream.rangeClosed(1, 13).mapToObj(i -> new Card(s, i))).collect(Collectors.toList());
    }

    public Deck() {
        this(0);
    }

    public Deck(int addJokers) {
        if (addJokers > 0) IntStream.range(0, addJokers).forEach(i -> bill.add(new Card(Suit.JOKER, 0)));
        Collections.shuffle(bill);
        maxSize = bill.size();
    }

    public Card draw() {
        return bill.remove(0);
    }
}
