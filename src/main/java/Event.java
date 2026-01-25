import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    Event(String taskName, LocalDate startDate, LocalDate endDate, Boolean isCompleted) {
        super(taskName, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Event mark() {
        if (!this.isCompleted) {
            return new Event(this.taskName,
                    this.startDate, this.endDate,
                    true);
        }
        return this;
    }

    @Override
    public Event unmark() {
        if (this.isCompleted) {
            return new Event(this.taskName,
                    this.startDate, this.endDate,
                    false);
        }
        return this;
    }

    @Override
    public String getTaskIcon() {
        // e.g. "[E] []"
        return "[E] " + super.getTaskIcon();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d'th' yyyy");
        String formattedStartDate = this.startDate.format(formatter);
        String formattedEndDate = this.endDate.format(formatter);
        return super.toString() + " (from: " + formattedStartDate + " to: " + formattedEndDate + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d'th' yyyy");
        String formattedStartDate = this.startDate.format(formatter);
        String formattedEndDate = this.endDate.format(formatter);
        return "E | " + (this.isCompleted ? "1" : "0") + " | " +
                this.taskName + " | " + formattedStartDate + " | " +
                formattedEndDate;
    }
}
