package joe.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void testMarkAsDoneAndMarkAsNotDone() {
        // Create a task initially not done
        Task task = new Task("Read book");
        assertEquals("[0] Read book", task.toString(), "Task should start as not done");

        // Mark as done
        task.markAsDone();
        assertEquals("[1] Read book", task.toString(), "Task should be marked as done");

        // Mark as not done again
        task.markAsNotDone();
        assertEquals("[0] Read book", task.toString(), "Task should be marked as not done again");
    }

    @Test
    void testToStringReflectsIsDoneCorrectly() {
        // Task created as done
        Task doneTask = new Task("Write report", true);
        assertEquals("[1] Write report", doneTask.toString(), "toString should reflect done status");

        // Task created as not done
        Task notDoneTask = new Task("Go jogging", false);
        assertEquals("[0] Go jogging", notDoneTask.toString(), "toString should reflect not done status");
    }
}
