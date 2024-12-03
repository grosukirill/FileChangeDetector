package fileInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProgramFileInfo extends FileInfo {

    public ProgramFileInfo(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileInfo() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(this.getFilePath()));
        int lineCount = lines.size();
        long classCount = lines.stream().filter(line -> line.trim().startsWith("class ")).count();
        long methodCount = lines.stream().filter(line -> line.trim().endsWith("()")).count();

        return "Line Count: " + lineCount + "\n" +
                "Class Count: " + classCount + "\n" +
                "Method Count: " + methodCount + "\n";
    }
}
