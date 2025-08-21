class Deadline extends Task {
    // deadline return book /by Sunday
    public Deadline(String description, String by) {
        super(description + " (by: " + by + ")");
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }
}