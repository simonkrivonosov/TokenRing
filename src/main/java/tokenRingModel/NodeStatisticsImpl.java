package tokenRingModel;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Path.of;
import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class NodeStatisticsImpl implements NodeStatistics {
    private final String destination;

    private final List<Long> times = new ArrayList<>();
    private final List<Long> timestamps = new ArrayList<>();

    @Override
    public void record(long time) {
        times.add(time);
        timestamps.add(System.nanoTime());
    }

    @Override
    public void save() throws IOException {
        String result = times.stream()
                .map(String::valueOf)
                .collect(joining(" ")) + "\n" +
                timestamps.stream()
                        .map(String::valueOf)
                        .collect(joining(" "));
        Files.writeString(of(destination), result);
    }
}
