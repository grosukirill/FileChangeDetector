package snapshot;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class SnapshotManagerImpl implements SnapshotManager {
    private final String snapshotFilePath;
    private long lastCommitTime;

    public SnapshotManagerImpl(String snapshotFilePath) {
        this.snapshotFilePath = snapshotFilePath;
        this.lastCommitTime = System.currentTimeMillis();
    }

    public long getLastCommitTime() {
        return lastCommitTime;
    }

    @Override
    public Map<String, String> loadSnapshots() throws IOException {
        Map<String, String> snapshots = new HashMap<>();
        File file = new File(snapshotFilePath);

        if (!file.exists()) {
            return snapshots;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                snapshots.put(parts[0], parts[1]);
            }
        }
        return snapshots;
    }

    @Override
    public void saveSnapshots(Map<String, String> snapshots) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(snapshotFilePath))) {
            for (Map.Entry<String, String> entry : snapshots.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
    }

    @Override
    public void commit(Map<String, String> newSnapshots) throws IOException {
        saveSnapshots(newSnapshots);
        lastCommitTime = System.currentTimeMillis();
    }
}
