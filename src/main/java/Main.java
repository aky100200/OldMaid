import oldmaidgame.OldMaidGame;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        OldMaidGame oldMaidGame = new OldMaidGame();
        IntStream.of(2, 3, 5, 10).forEach(playerCnt -> {
            oldMaidGame.setPlayerCnt(playerCnt);
            double d = IntStream.range(0, 1000).map(i -> oldMaidGame.start()).average().orElseGet(() -> 0);
            BigDecimal r = BigDecimal.valueOf(d).setScale(1, BigDecimal.ROUND_HALF_UP);
            System.out.println("プレイヤー " + playerCnt + " 名のゲーム終了までの平均ターンは " + r + " ターンでした。");
        });
    }
}