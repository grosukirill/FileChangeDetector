package changeDetector;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileChangeDetector {

    Map<String, Boolean> getStatus(List<String> filePaths) throws IOException;

}
