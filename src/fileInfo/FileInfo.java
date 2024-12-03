package fileInfo;

import java.io.IOException;

public class FileInfo {
    private final String filePath;


    public FileInfo(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileInfo() throws IOException {
        return "";
    }
}
