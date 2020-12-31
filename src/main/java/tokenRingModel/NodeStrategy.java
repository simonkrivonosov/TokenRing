package tokenRingModel;

public interface NodeStrategy {
    void setId(int id);

    void setNumOfNodes(int numOfNodes);

    Token process(Token token) throws InterruptedException;
}
