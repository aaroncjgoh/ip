package joe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static String formatDates(String by) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedFrom = LocalDate.parse(by, inputFormat).format(outputFormat);

        return " (by: " + formattedFrom + ")";
    }

    public Deadline(String description, String by) {
        super(description + formatDates(by));
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description + formatDates(by), isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}