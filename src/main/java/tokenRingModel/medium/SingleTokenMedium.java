package tokenRingModel.medium;

import lombok.RequiredArgsConstructor;
import tokenRingModel.Token;
import tokenRingModel.TokenMediumBuilder;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


@RequiredArgsConstructor
public class SingleTokenMedium implements TokenMedium {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private volatile Token token = null;

    public static TokenMediumBuilder singleTokenMediumBuilder() {
        return SingleTokenMedium::new;
    }

    @Override
    public Token receiveToken() throws InterruptedException {
        lock.lock();
        try {
            while (token == null) {
                condition.await();
            }
            Token myToken = token;
            token = null;
            condition.signal();
            return myToken;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void sendToken(Token newToken) throws InterruptedException {
        lock.lock();
        try {
            while (token != null) {
                condition.await();
            }
            token = newToken;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
