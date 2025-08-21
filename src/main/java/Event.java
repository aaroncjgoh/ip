class Event extends Task {
    public Event(String description, String from, String to) {
        super(description + " (from: " + from + " to: " + to + ")");
    }   

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}