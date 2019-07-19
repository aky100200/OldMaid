package card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Card {
    public static final int MAX_SIZE = 53;
    private final Suit suit;
    @Getter
    private final int rank;
}
