class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.taskList = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void executeCommand(String input) throws InvalidJoeInputException {
        String[] parts = input.split(" ");
        String command = parts[0];
        switch (command) {
        case "bye": {
            break;
        }

        case "list": {
            this.ui.print_todoList(taskList);
            break;
        }

        case "mark": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            this.taskList.markTaskAsDone(Integer.parseInt(parts[1]));
            this.storage.logTodoList(this.taskList);
            this.ui.line();
            break;
        }

        case "unmark": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            this.taskList.markTaskAsNotDone(Integer.parseInt(parts[1]));
            this.storage.logTodoList(taskList);
            this.ui.line();
            break;
        }

        case "todo": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" ", 2)[1];
            this.taskList.addToList(new ToDo(description));
            this.storage.logTodoList(taskList);
            this.ui.line();
            break;
        }

        case "deadline": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" ", 2)[1].split(" /by ")[0].trim();
            String by = input.split(" /by ")[1].trim();
            this.taskList.addToList(new Deadline(description, by));
            this.storage.logTodoList(taskList);
            this.ui.line();
            break;
        }

        case "event": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            String description = input.split(" /from ")[0].split(" ", 2)[1].trim();
            String from = input.split(" /from ")[1].split(" /to ")[0].trim();
            String to = input.split(" /to ")[1].trim();
            this.taskList.addToList(new Event(description, from, to));
            this.storage.logTodoList(taskList);
            this.ui.line();
            break;
        }

        case "delete": {
            if (parts.length < 2) {
                throw new InvalidJoeInputException(command);
            }

            if (Integer.parseInt(parts[1]) > this.taskList.getLength() || Integer.parseInt(parts[1]) < 1) {
                throw new InvalidJoeInputException(command, "Invalid index");
            }

            int index = Integer.parseInt(parts[1]) - 1;
            this.taskList.deleteFromList(index);
            this.storage.logTodoList(taskList);
            this.ui.line();
            break;
        }

        default: {
            throw new InvalidJoeInputException();
        }
        }

    }
}