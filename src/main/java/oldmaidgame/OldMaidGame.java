package oldmaidgame;

import card.Card;
import card.Deck;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import player.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class OldMaidGame {

    private final int playerCnt;

    /**
     * ゲーム開始
     *
     * @return ゲーム終了までにかかったターン数
     */
    public int start() {
        Deck deck = new Deck(1);
        List<Player>  players = IntStream.rangeClosed(1, playerCnt).mapToObj(i -> new Player(i)).collect(Collectors.toList());
        initPlayerHand(players, deck);
        players.removeAll(players.stream().filter(p -> p.getCardList().isEmpty()).collect(Collectors.toList()));

        int turnCnt = 0;
        for (; 1 < players.size(); turnCnt++) {
            Player drawer = players.get(turnCnt % players.size());
            Player target = players.get((turnCnt + 1) % players.size());
            Card drawCard = drawer.draw(target);
            throwPairCard(drawer,drawCard);
            if (drawer.getCardList().isEmpty()) players.remove(drawer);
            if (target.getCardList().isEmpty()) players.remove(target);
        }
        return turnCnt / playerCnt;
    }

    private void initPlayerHand(List<Player> players, Deck deck) {
        int base = deck.getMaxSize() / playerCnt;
        int fraction = deck.getMaxSize() % playerCnt;
        for (Player player : players) {
            if (fraction-- > 0) {
                player.draw(deck, base + 1);
                continue;
            }
            player.draw(deck, base);
        }
        players.forEach(p -> initThrowPairCard(p));
    }

    /**
     * 手札でペアとなったカードを捨てる
     * @param p プレイヤー
     */
    public void initThrowPairCard(Player p) {
        List<Card> handCardList = p.getCardList();
        List<Card> discardedList = Lists.newArrayList();
        for(Card card1 : handCardList){
            if (discardedList.contains(card1)) continue;
            for(Card card2 : handCardList){
                if (card1 == card2 || discardedList.contains(card2)) continue;
                if (card1.getRank() == card2.getRank()){
                    discardedList.add(card1);
                    discardedList.add(card2);
                    break;
                }
            }
        }
        handCardList.removeAll(discardedList);
    }

    /**
     * 手札でペアとなったカードを捨てる
     *
     * @param p プレイヤー
     */
    public void throwPairCard(Player p, Card drawCard) {
        List<Card> handCardList = p.getCardList();
        for (Card handCard : handCardList) {
            if (drawCard == handCard) continue;
            if (drawCard.getRank() == handCard.getRank()) {
                handCardList.remove(drawCard);
                handCardList.remove(handCard);
                return;
            }
        }
    }

}
