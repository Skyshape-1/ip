public class Deadline extends Task {
    private final String dueDate;

    Deadline(String taskName, String dueDate, Boolean isCompleted) {
        super(taskName, isCompleted);
        this.dueDate = dueDate;
    }

    Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public Deadline mark() {
        if (!this.isCompleted) {
            return new Deadline(this.taskName, this.dueDate, true);
        }
        return this;
    }

    @Override
    public Deadline unmark() {
        if (this.isCompleted) {
            return new Deadline(this.taskName, this.dueDate, false);
        }
        return this;
    }

    @Override
    public String getTaskIcon() {
        return "[D] " + super.getTaskIcon();
    }

    @Override
    public String toString() {
        // e.g. "return book (by: June 6th)"
        return super.toString() + " (by: " + this.dueDate + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isCompleted ? "1" : "0") + " | " + this.taskName + " | " + this.dueDate;
    }
}
