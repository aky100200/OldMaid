package card;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Suit {
    SPADE("スペード"),
    HEART("ハート"),
    DIAMOND("ダイヤ"),
    CLUB("クラブ"),
    JOKER("ジョーカー");

    @Getter
    private final String mark;
}
