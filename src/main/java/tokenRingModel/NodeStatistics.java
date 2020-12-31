package tokenRingModel;

import java.io.IOException;

public interface NodeStatistics {
    void record(long time);

    void save() throws IOException;
}
