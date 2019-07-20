package oldmaidgame;

import card.Deck;
import lombok.Getter;
import lombok.Setter;
import player.Player;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class OldMaidGame {

    @Getter
    @Setter
    private int playerCnt;

    /**
     * ゲーム開始
     *
     * @return ゲーム終了までにかかったターン数
     */
    public int start() {
        Deck deck = new Deck(1);
        Player[] players = IntStream.rangeClosed(1, playerCnt).mapToObj(i -> new Player(i)).toArray(Player[]::new);
        initPlayerHand(players, deck);

        int turnCnt = 0;
        while (!isOnlyPlayer(players)) {
            for (int i = 0; i < players.length; i++) {
                Player drawer = nextPlayer(players, i);
                Player target = nextPlayer(players, i, drawer);
                drawer.draw(target);
                if (isOnlyPlayer(players)) break;
            }
            turnCnt++;
        }
        return turnCnt;
    }

    private void initPlayerHand(Player[] players, Deck deck) {
        int base = deck.getMaxSize() / playerCnt;
        int fraction = deck.getMaxSize() % playerCnt;
        for (Player player : players) {
            if (fraction-- > 0) {
                player.draw(deck, base + 1);
                continue;
            }
            player.draw(deck, base);
        }
        Arrays.stream(players).forEach(p -> p.throwCard());
    }

    private boolean isOnlyPlayer(Player[] players) {
        return Arrays.stream(players).filter(p -> !p.isFinished()).count() == 1;
    }

    private Player nextPlayer(Player[] players, int i) {
        return nextPlayer(players, i, null);
    }

    private Player nextPlayer(Player[] players, int i, Player exclude) {
        for (Player player : players) {
            if (Objects.equals(players[i], exclude)) {
                i = i == players.length - 1 ? 0 : i + 1;
                continue;
            }
            if (!players[i].isFinished()) return players[i];
            i = i == players.length - 1 ? 0 : i + 1;
        }
        throw new RuntimeException("Player Not Found");
    }
}
