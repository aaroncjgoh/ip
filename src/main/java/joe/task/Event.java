package joe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static String formatDates(String from, String to) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedFrom = LocalDate.parse(from, inputFormat).format(outputFormat);
        String formattedTo = LocalDate.parse(to, inputFormat).format(outputFormat);

        return " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    public Event(String description, String from, String to) {
        super(description + formatDates(from, to));
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description + formatDates(from, to), isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}