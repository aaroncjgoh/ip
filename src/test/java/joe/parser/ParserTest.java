package joe.parser;

import joe.exception.InvalidJoeInputException;
import joe.storage.Storage;
import joe.task.TaskList;
import joe.task.ToDo;
import joe.ui.Ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class ParserTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(null);
        storage = mock(Storage.class); // mock storage so we don't write to file
        ui = mock(Ui.class); // mock UI
        parser = new Parser(taskList, storage, ui);
    }

    @Test
    void testTodoCommandAddsTask() throws InvalidJoeInputException {
        parser.executeCommand("todo Buy milk");

        // Check task list has one task
        assertEquals(1, taskList.getLength());
        assertTrue(taskList.getTask(0) instanceof ToDo);
        assertEquals("[T][0] Buy milk", taskList.getTask(0).toString());

        // Verify UI and storage were called
        verify(storage).logTodoList(taskList);
        verify(ui).line();
    }

    @Test
    void testMarkCommandMarksTaskAsDone() throws InvalidJoeInputException {
        // Add a task first
        parser.executeCommand("todo Read book");

        // Mark the first task
        parser.executeCommand("mark 1");

        assertEquals("[T][1] Read book", taskList.getTask(0).toString());

        // Verify UI and storage were called
        verify(storage, times(2)).logTodoList(taskList); // once for todo, once for mark
        verify(ui, times(2)).line();
    }

    @Test
    void testInvalidCommandThrowsException() {
        Exception exception = assertThrows(InvalidJoeInputException.class, () -> {
            parser.executeCommand("foobar");
        });

        assertNotNull(exception.getMessage());
    }
}
