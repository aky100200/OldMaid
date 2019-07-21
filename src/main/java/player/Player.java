package player;

import card.Card;
import card.Deck;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class Player {

    private static final String DEFAULT_NAME = "プレイヤー";

    private final String name;
    private List<Card> cardList;

    public Player(int i) {
        this.name = DEFAULT_NAME + i;
        this.cardList = Lists.newArrayList();
    }

    /**
     * 山札からカードを引く
     *
     * @param deck 山札
     * @param i    引く枚数
     */
    public void draw(Deck deck, int i) {
        IntStream.range(0, i).forEach(ii -> {
            Card card = deck.draw();
            cardList.add(card);
        });
    }

    /**
     * 他プレイヤーの手札からカードを引く
     *
     * @param target カードを引く対象プレイヤー
     */
    public Card draw(Player target) {
        Card card = target.supplyCard();
        this.cardList.add(card);
        return card;
    }

    protected Card supplyCard() {
        return supplyCard(0, true);
    }

    private Card supplyCard(int index, boolean isShuffle) {
        if (isShuffle) Collections.shuffle(this.cardList);
        return this.cardList.remove(index);
    }

}
