package chatbot;

import chatbot.commands.*;
import chatbot.tasks.TaskList;

public class Parser {
    public static Command readCommand(TaskList taskList, String input) throws ChatbotException {
        String[] inputArray = input.split(" ", 2);
        String commandName = inputArray[0];
        String description = "";
        if (inputArray.length > 1) {
            description = inputArray[1];
        }
        switch (commandName) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand(taskList);
        case "unmark":
            return new UnmarkCommand(taskList, description);
        case "mark":
            return new MarkCommand(taskList, description);
        case "delete":
            return new DeleteCommand(taskList, description);
        case "todo":
            return new TodoCommand(taskList, description);
        case "deadline":
            return new DeadlineCommand(taskList, description);
        case "event":
            return new EventCommand(taskList, description);
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand(taskList, description);
        default:
            throw new ChatbotException("I don't know what that is. Type 'help' for help. \n " +
                    "------------------------------");
        }
    }
}