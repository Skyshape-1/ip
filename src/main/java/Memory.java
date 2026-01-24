import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Memory {
    private final String filePath;

    public Memory(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws MiliException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new MiliFileNotFoundException("Previous memory not found.");
        }

        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (MiliCorruptDataException e) {
                    System.out.println("Skipping corrupt task line: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new MiliException("Error loading tasks from memory: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws MiliException {
        try {
            ensureDirectoryExists();
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new MiliException("Error saving tasks to memory: " + e.getMessage());
        }
    }

    private void ensureDirectoryExists() throws IOException {
        Path path = Paths.get(filePath);
        Path parent = path.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private Task parseTask(String line) throws MiliCorruptDataException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new MiliCorruptDataException("Incomplete data fields in: " + line);
        }

        String type = parts[0];
        String isDoneStr = parts[1];
        if (!isDoneStr.equals("0") && !isDoneStr.equals("1")) {
            throw new MiliCorruptDataException("Invalid completion status: " + isDoneStr);
        }
        boolean isDone = isDoneStr.equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                if (parts.length != 3) {
                    throw new MiliCorruptDataException("Unexpected fields for Todo: " + line);
                }
                task = new Todo(description, isDone);
                break;
            case "D":
                if (parts.length != 4) {
                    throw new MiliCorruptDataException("Unexpected fields for Deadline: " + line);
                }
                task = new Deadline(description, parts[3], isDone);
                break;
            case "E":
                if (parts.length != 5) {
                    throw new MiliCorruptDataException("Unexpected fields for Event: " + line);
                }
                task = new Event(description, parts[3], parts[4], isDone);
                break;
            default:
                throw new MiliCorruptDataException("Unknown task type: " + type);
        }
        return task;
    }
}
