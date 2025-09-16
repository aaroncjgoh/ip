package joe.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Type of task that contains a description and due date
 */
public class Deadline extends Task {
    private String deadline;

    private static String formatDates(String by) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        String formattedFrom = LocalDate.parse(by, inputFormat).format(outputFormat);

        return " (by: " + formattedFrom + ")";
    }

    /**
     * Creates a Deadline task that has not been completed.
     * 
     * @param description Description of the task.
     * @param by Due date of the task.
     */
    public Deadline(String description, String by) {
        super(description + formatDates(by));
        this.deadline = by;
    }

    /**
     * Creates a Deadline task whose completion status can be specified.
     * 
     * @param description Description of the task.
     * @param by Due date of the task.
     * @param isDone Completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description + formatDates(by), isDone);
        this.deadline = by;
    }

    public String getNextDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
