package fileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextFileInfo extends FileInfo {

    public TextFileInfo(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileInfo() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(this.getFilePath()));
        int lineCount = lines.size();
        int wordCount = lines.stream().mapToInt(line -> line.split("\\s+").length).sum();
        int charCount = lines.stream().mapToInt(String::length).sum();

        return "Line Count: " + lineCount + "\n" +
                "Word Count: " + wordCount + "\n" +
                "Character Count: " + charCount + "\n";
    }
}
