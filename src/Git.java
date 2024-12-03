import changeDetector.FileChangeDetector;
import changeDetector.FileChangeDetectorImpl;
import snapshot.FileSnapshot;
import snapshot.SnapshotManager;
import snapshot.SnapshotManagerImpl;

import java.io.IOException;
import java.util.*;


public class Git {
    private static final String FILE_SOURCE = "src/files/";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SnapshotManager snapshotManagerImpl = new SnapshotManagerImpl("snapshots.txt");
        FileChangeDetector detector = new FileChangeDetectorImpl(snapshotManagerImpl);

        List<String> files = Arrays.asList(FILE_SOURCE + "test.txt", FILE_SOURCE + "test.py", FILE_SOURCE + "test.jpg");

        while (true) {
            System.out.println("Enter a command (commit, info, status, exit): ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "commit":
                    Map<String, String> newSnapshots = new HashMap<>();
                    for (String file : files) {
                        newSnapshots.put(file, new FileSnapshot(file).getFileHash());
                    }
                    snapshotManagerImpl.commit(newSnapshots);
                    System.out.println("Snapshot committed.");
                    break;

                case "info":
                    System.out.println("Enter file name: ");
                    String fileName = scanner.nextLine();
                    try {
                        FileSnapshot snapshot = new FileSnapshot(FILE_SOURCE + fileName);
                        System.out.println(snapshot.getFileInfo());
                    } catch (IOException e) {
                        System.out.println("File not found.");
                    }
                    break;

                case "status":
                    Map<String, Boolean> status = detector.getStatus(files);
                    status.forEach((key, value) -> System.out.println(key + ": " + (value ? "Changed" : "Unchanged")));
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
