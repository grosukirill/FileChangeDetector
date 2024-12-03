package snapshot;

import java.io.IOException;
import java.util.Map;

public interface SnapshotManager {

    Map<String, String> loadSnapshots() throws IOException;

    void saveSnapshots(Map<String, String> snapshots) throws IOException;

    void commit(Map<String, String> newSnapshots) throws IOException;

}
