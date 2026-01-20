public class Todo extends Task {

    Todo(String taskName, Boolean isCompleted) {
        super(taskName, isCompleted);
    }

    Todo(String taskName) {
        super(taskName);
    }

    @Override
    public Todo mark() {
        if (!this.isCompleted) {
            return new Todo(this.taskName, true);
        }
        return this;
    }

    @Override
    public Todo unmark() {
        if (this.isCompleted) {
            return new Todo(this.taskName, false);
        }
        return this;
    }

    @Override
    public String getTaskIcon() {
        return "[T] " + super.getTaskIcon();
    }
}
