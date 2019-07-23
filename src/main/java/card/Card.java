package card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Card {
    private final Suit suit;
    @Getter
    private final int rank;
}
