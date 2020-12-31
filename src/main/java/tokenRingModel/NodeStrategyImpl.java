package tokenRingModel;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Random;

@RequiredArgsConstructor
public class NodeStrategyImpl implements NodeStrategy {
    private final NodeStatistics stats;
    private final Random random = new Random();

    @Setter
    private int id = 0;
    @Setter
    private int numOfNodes = 1;
    private Integer waitingToken = null;
    private long startWaiting = 0;

    @Override
    public Token process(Token token) {
        finishWaiting(token);
        processTokenSentForMe(token);
        sendNewToken(token);
        return token;
    }

    private void sendNewToken(Token token) {
        while (token.destinationId() == null) {
            int destination = random.nextInt(numOfNodes);
            if (destination == id) continue;
            token.destinationId(destination);
            waitingToken = token.id();
            startWaiting = System.nanoTime();
            System.out.println("[" + id + "] Sent token[" + waitingToken + "] to " + destination);
        }
    }

    private void processTokenSentForMe(Token token) {
        if (token.destinationId() != null && token.destinationId().equals(id)) {
            System.out.println("[" + id + "] Got token[" + token.id() + "]");
            System.out.println("[" + id + "] Processed token[" + token.id() + "]");
        }
    }

    private void finishWaiting(Token token) {
        if (waitingToken != null && waitingToken.equals(token.id())) {
            token.destinationId(null);
            waitingToken = null;
            stats.record(System.nanoTime() - startWaiting);
            System.out.println("[" + id + "] Received token[" + token.id() + "] back");
        }
    }
}
