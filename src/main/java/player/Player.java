package player;

import card.Card;
import card.Deck;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Player {

    private static final String DEFAULT_NAME = "プレイヤー";

    @Getter
    private final String name;
    private List<Card> cardList;
    @Getter
    private boolean isFinished;

    public Player(int i) {
        this.name = DEFAULT_NAME + i;
        cardList = Lists.newArrayList();
    }

    /**
     * 山札からカードを引く
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
     * 不要なカードを手札から除外する
     */
    public void throwCard() {
        List<Card> discardedList = Lists.newArrayList();
        for(Card card1 : cardList){
            if (discardedList.contains(card1)) continue;
            for(Card card2 : cardList){
                if (card1 == card2 || discardedList.contains(card2)) continue;
                if (card1.getRank() == card2.getRank()){
                    discardedList.add(card1);
                    discardedList.add(card2);
                    break;
                }
            }
        }
        discardedList.forEach(c -> cardList.remove(c));
        isFinished = cardList.isEmpty();
    }

    /**
     * 他プレイヤーの手札からカードを引く
     * @param target カードを引く対象プレイヤー
     */
    public void draw(Player target) {
        Card card = target.supplyCard();
        this.cardList.add(card);
        throwCard();
    }

    private Card supplyCard() {
        Collections.shuffle(this.cardList);
        Card card = this.cardList.remove(0);
        this.isFinished = this.cardList.isEmpty();
        return card;
    }

}
