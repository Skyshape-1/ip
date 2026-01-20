public class Task {
    private final String taskName;
    private final boolean isCompleted;

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    Task(String taskName) {
        this(taskName, false);
    }

    public String getStatusIcon() {
        if (this.isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
