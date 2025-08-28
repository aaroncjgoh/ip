class Event extends Task {
    public Event(String description, String from, String to) {
        super(description + " (from: " + from + " to: " + to + ")");
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description + " (from: " + from + " to: " + to + ")", isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}