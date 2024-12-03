package snapshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Snapshot {
    private final String filePath;
    private final String fileHash;
    private final String fileName;
    private final String extension;
    private final long creationTime;
    private final long lastModifiedTime;

    public Snapshot(String filePath) throws IOException {
        this.filePath = filePath;
        Path path = Path.of(filePath);

        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
        this.fileName = path.getFileName().toString();
        this.extension = getFileExtension(fileName);
        this.creationTime = attrs.creationTime().toMillis();
        this.lastModifiedTime = attrs.lastModifiedTime().toMillis();
        this.fileHash = calculateHash();
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileHash() {
        return fileHash;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    private String calculateHash() throws IOException {
        byte[] fileContent = Files.readAllBytes(Path.of(getFilePath()));
        int hash = 0;
        for (byte b : fileContent) {
            hash += b;
        }
        return Integer.toHexString(hash);
    }

    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return (index != -1) ? fileName.substring(index + 1) : "";
    }
}
