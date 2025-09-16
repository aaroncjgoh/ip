package joe.parser;

import joe.exception.InvalidJoeInputException;
import joe.storage.Storage;
import joe.task.Deadline;
import joe.task.Event;
import joe.task.TaskList;
import joe.task.ToDo;
import joe.task.Task;
import joe.ui.Ui;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a parser object that deals with inputs given by the user.
     * 
     * @param tasks TaskList object of tasks.
     * @param storage Storage object tracking memory.
     * @param ui Ui object handling displays in the terminal.
     */
    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.taskList = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Takes in the command input by the user and performs the respective actions.
     * 
     * @param input Input command given by user.
     * @throws InvalidJoeInputException If command is not recognized.
     */
    public String executeCommand(String input) throws InvalidJoeInputException {
        assert !input.isEmpty() : "Input text shouldn't be empty";
        String output;
        String[] parts = input.split(" ");
        String command = parts[0];
        switch (command) {
        case "bye": {
            return "";
        }

        case "list": {
            return this.ui.printTodoList(taskList);
        }

        case "mark": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            output = this.taskList.markTaskAsDone(Integer.parseInt(parts[1]));
            this.storage.logTodoList(this.taskList);
            return output;
        }

        case "unmark": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            output = this.taskList.markTaskAsNotDone(Integer.parseInt(parts[1]));
            this.storage.logTodoList(taskList);
            return output;
        }

        case "todo": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" ", 2)[1];
            output = this.taskList.addToList(new ToDo(description));
            this.storage.logTodoList(taskList);
            return output;
        }

        case "deadline": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" ", 2)[1].split(" /by ")[0].trim();
            String by = input.split(" /by ")[1].trim();
            output = this.taskList.addToList(new Deadline(description, by));
            this.storage.logTodoList(taskList);
            return output;
        }

        case "event": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" /from ")[0].split(" ", 2)[1].trim();
            String from = input.split(" /from ")[1].split(" /to ")[0].trim();
            String to = input.split(" /to ")[1].trim();
            output = this.taskList.addToList(new Event(description, from, to));
            this.storage.logTodoList(taskList);
            return output;
        }

        case "delete": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            int index = Integer.parseInt(parts[1]) - 1;
            output = this.taskList.deleteFromList(index);
            this.storage.logTodoList(taskList);
            return output;
        }

        case "find": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }
            if (parts.length > 2) {
                throw new InvalidJoeInputException(command, "Only give one word");
            }
            String keyWord = input.split(" ", 2)[1];
            ArrayList<Task> tasks = new ArrayList<>(this.taskList.getTodoList());
            List<Task> matches = tasks.stream()
                    .filter(task -> Arrays.asList(task.toString().split(" ")).contains(keyWord)).toList();
            output = this.ui.printMatches(matches);
            return output;
        }

        default: {
            throw new InvalidJoeInputException();
        }
        }

    }
}
