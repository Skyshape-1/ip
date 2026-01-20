public class Event extends Task {
    private final String startDate;
    private final String endDate;

    Event(String taskName, String startDate, String endDate, Boolean isCompleted) {
        super(taskName, isCompleted);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Event(String taskName, String startDate, String endDate) {
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
        // e.g. "project meeting (from: Aug 6th 2pm to: 4pm)"
        return super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
