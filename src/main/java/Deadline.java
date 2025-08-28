class Deadline extends Task {
    // deadline return book /by Sunday
    public Deadline(String description, String by) {
        super(description + " (by: " + by + ")");
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description + " (by: " + by + ")", isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}