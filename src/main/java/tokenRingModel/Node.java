package tokenRingModel;

import java.util.List;
import lombok.RequiredArgsConstructor;
import tokenRingModel.medium.TokenMedium;


@RequiredArgsConstructor
public class Node {
    private final TokenMedium receiveMedium;
    private final TokenMedium sendMedium;
    private final NodeStrategy strategy;

    private volatile boolean stopped = false;
    private Thread thread = null;

    public void start(List<Token> initialTokens) throws InterruptedException {
        for (Token initialToken : initialTokens) {
            sendMedium.sendToken(initialToken);
        }
        thread = new Thread(runnable());
        thread.start();
    }

    public void stop() {
        stopped = true;
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            //interrupted
        }
    }

    private Runnable runnable() {
        return () -> {
            while (!stopped) {
                try {
                    Token token = receiveMedium.receiveToken();
                    sendMedium.sendToken(strategy.process(token));
                } catch (InterruptedException e) {
                    break;
                }
            }
        };
    }
}
