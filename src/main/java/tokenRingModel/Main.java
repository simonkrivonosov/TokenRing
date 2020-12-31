package tokenRingModel;

import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;


import static tokenRingModel.medium.SingleTokenMedium.singleTokenMediumBuilder;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        NodeStatistics stats = new NodeStatisticsImpl("/Users/simon/results882b.txt");
        NodeRunner nodeRunner = new NodeRunner(List.of(new NodeStrategyImpl(stats), new NodeStrategyImpl(stats),
                new NodeStrategyImpl(stats), new NodeStrategyImpl(stats), new NodeStrategyImpl(stats),
                new NodeStrategyImpl(stats), new NodeStrategyImpl(stats), new NodeStrategyImpl(stats)
        ), singleTokenMediumBuilder(), 2);

        nodeRunner.start();
        sleep(5 * 1000);
        nodeRunner.stop();
        stats.save();
    }
}
