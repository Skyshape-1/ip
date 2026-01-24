public abstract class Task {
    protected final String taskName;
    protected final boolean isCompleted;

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    Task(String taskName) {
        this(taskName, false);
    }

    public abstract Task unmark();

    public abstract Task mark();

    public String getTaskIcon() {
        String statusIcon = "[" + (this.isCompleted ? "X" : " ") + "]";
        return statusIcon;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    public abstract String toFileString();
}
