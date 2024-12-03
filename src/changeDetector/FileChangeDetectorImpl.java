package changeDetector;

import snapshot.FileSnapshot;
import snapshot.SnapshotManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileChangeDetectorImpl implements FileChangeDetector {
    private final SnapshotManager snapshotManager;

    public FileChangeDetectorImpl(SnapshotManager snapshotManager) {
        this.snapshotManager = snapshotManager;
    }

    public Map<String, Boolean> getStatus(List<String> filePaths) throws IOException {
        Map<String, Boolean> status = new HashMap<>();
        Map<String, String> oldSnapshots = snapshotManager.loadSnapshots();

        for (String filePath : filePaths) {
            String currentHash = new FileSnapshot(filePath).getFileHash();
            String oldHash = oldSnapshots.get(filePath);
            status.put(filePath, !currentHash.equals(oldHash));
        }

        return status;
    }
}
