import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {

        TokenRing tokenRing = new TokenRing(10);
        tokenRing.createRing();

        try {
            tokenRing.start();
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            tokenRing.interrupt();
        }
    }
}
