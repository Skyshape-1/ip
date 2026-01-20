public class Task {
    protected final String taskName;
    protected final boolean isCompleted;

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    Task(String taskName) {
        this(taskName, false);
    }

    public Task unmark() {
        if (this.isCompleted) {
            return new Task(this.taskName, false);
        }
        return this;
    }

    public Task mark() {
        if (!this.isCompleted) {
            return new Task(this.taskName, true);
        }
        return this;
    }

    public String getTaskIcon() {
        String statusIcon = "[" + (this.isCompleted ? "X" : " ") + "]";
        return statusIcon;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
