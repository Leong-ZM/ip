package chatbot.commands;

import chatbot.ChatbotException;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.UI;

import java.util.Scanner;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand implements Command {
    private final TaskList taskList;
    private final int taskNum;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskList The list of tasks.
     * @param description The description of the task.
     */
    public DeleteCommand(TaskList taskList, String description) {
        this.taskList = taskList;
        this.taskNum = (new Scanner(description).useDelimiter("\\D+").nextInt()) - 1;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param isReading If the chatbot is reading a file, during which it will not display confirmation text.
     * @throws ChatbotException If index out of bounds.
     */
    @Override
    public void execute(boolean isReading) throws ChatbotException {
        if (taskNum >= taskList.getListLength()) {
            throw new ChatbotException("You only have " + taskList.getListLength() +
                    " tasks. Choose one of them. ");
        }
        Task selectedTask = taskList.get(taskNum);
        taskList.delete(taskNum);
        UI.printDeleteReply();
        selectedTask.printData();
        UI.printSeparator();
    }
}
