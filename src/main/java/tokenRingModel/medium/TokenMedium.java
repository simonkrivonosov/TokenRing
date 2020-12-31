package tokenRingModel.medium;

import tokenRingModel.Token;

public interface TokenMedium {
    Token receiveToken() throws InterruptedException;

    void sendToken(Token token) throws InterruptedException;
}
