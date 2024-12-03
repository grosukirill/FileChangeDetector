package snapshot;

import fileInfo.FileInfo;
import fileInfo.ImageFileInfo;
import fileInfo.ProgramFileInfo;
import fileInfo.TextFileInfo;

import java.io.IOException;


public class FileSnapshot extends Snapshot {

    private FileInfo fileInfo;

    public FileSnapshot(String filePath) throws IOException {
        super(filePath);
    }

    public String getFileInfo() throws IOException {
        StringBuilder info = new StringBuilder();
        info.append("Filename: ").append(getFileName()).append("\n");
        info.append("Extension: ").append(getExtension()).append("\n");
        info.append("Creation Date: ").append(getCreationTime()).append("\n");
        info.append("Last Updated: ").append(getLastModifiedTime()).append("\n");

        switch (getExtension().toLowerCase()) {
            case "png":
            case "jpg":
                info.append(getImageFileInfo());
                break;
            case "txt":
                info.append(getTextFileInfo());
                break;
            case "java":
            case "py":
                info.append(getProgramFileInfo());
                break;
            default:
                info.append("No additional info for this file type.\n");
        }

        return info.toString();
    }

    private String getImageFileInfo() throws IOException {
        // Mock dimensions for demonstration
        fileInfo = new ImageFileInfo(getFilePath());
        return fileInfo.getFileInfo();
    }

    private String getTextFileInfo() throws IOException {
        fileInfo = new TextFileInfo(getFilePath());
        return fileInfo.getFileInfo();
    }

    private String getProgramFileInfo() throws IOException {
        fileInfo = new ProgramFileInfo(getFilePath());
        return fileInfo.getFileInfo();
    }
}
