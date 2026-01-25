import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate dueDate;

    Deadline(String taskName, LocalDate dueDate, Boolean isCompleted) {
        super(taskName, isCompleted);
        this.dueDate = dueDate;
    }

    Deadline(String taskName, LocalDate dueDate) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d'th' yyyy");
        String formattedDate = this.dueDate.format(formatter);
        return super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.isCompleted ? "1" : "0") + " | " + this.taskName + " | " + this.dueDate;
    }
}
