package chatman.commands;

import chatman.exceptions.EmptyListException;
import chatman.exceptions.IncorrectArgumentNumException;
import chatman.exceptions.IncorrectFormatException;
import chatman.tasks.Task;
import chatman.utility.Tasklist;
import chatman.utility.Ui;

/**
 * Implements ChatMan's ability to find a task by searching with a keyword.
 *
 * @author LWachtel1
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand; invokes superclass constructor.
     *
     * @param userCommand Receives and stores user-entered command (from Parser object) to use in perform()
     *                    method.
     */
    public FindCommand(String userCommand) {
        super((userCommand));
    }

    /**
     * Prints all tasks whose descriptions contain the keyword argument supplied with 'find KEYWORD_ARG'.
     *
     * @throws IncorrectFormatException If command is entered without required formatting of arguments.
     * @throws IncorrectArgumentNumException If command provided with incorrect number of arguments.
     */
    public void perform() throws IncorrectArgumentNumException, IncorrectFormatException {

        String[] findCommand = userCommand.split(" ", 2);

        //Ensures an argument has been provided (find KEYWORD_ARG)
        if (findCommand.length != 2) {
            throw new IncorrectArgumentNumException("FIND", userCommand);
        }

        //checks for any / which would mean unnecessary arguments
        for (String argument : findCommand) {
            if (argument.contains("/")) {
                throw new IncorrectArgumentNumException("FIND", argument);
            }
        }

        String keyword = findCommand[1].trim();

        if (keyword.isEmpty()) {
            throw new IncorrectFormatException("FIND", userCommand);
        }

        System.out.printf("%s%n", Ui.getChatbotSeparator());
        System.out.printf("Here are the full matches in your list for '%s':%n", keyword);

        for (int i = 0; i < Tasklist.getSize(); i++) {

            Task currentTaskToCheck = Tasklist.getTask(i);
            String taskDesc = currentTaskToCheck.getDescription();

            if (taskDesc.contains(keyword)) {
                System.out.printf("%d.%s%n", (i + 1), currentTaskToCheck.toString());
            }
        }

    }

}
