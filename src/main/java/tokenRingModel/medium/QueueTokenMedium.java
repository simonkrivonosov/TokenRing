package tokenRingModel.medium;

import tokenRingModel.Token;
import tokenRingModel.TokenMediumBuilder;

import java.util.concurrent.ArrayBlockingQueue;


public class QueueTokenMedium implements TokenMedium {
    //private final SynchronousQueue<Token> tokens;
    private final ArrayBlockingQueue<Token> tokens;

    public QueueTokenMedium(int capacity) {
        tokens = new ArrayBlockingQueue<>(capacity);
    }

    public static TokenMediumBuilder queueTokenMediumBuilder(int capacity) {
        return () -> new QueueTokenMedium(capacity);
    }

    @Override
    public Token receiveToken() throws InterruptedException {
        return tokens.take();
    }

    @Override
    public void sendToken(Token token) throws InterruptedException {
        tokens.put(token);
    }
}
