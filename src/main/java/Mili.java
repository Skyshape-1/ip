import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mili {
    private static final String HOR_DIV_LINE = "------------------------------------------------";
    public static void main(String[] args) {

        ArrayList<Task> storedTasks = new ArrayList<Task>(100);

        printLogo();
        wrapper(greet());

        Scanner sc = new Scanner(System.in);

        String nextMessage = sc.nextLine();
        String commandType, response;
        while(true) {

            
            try {
                commandType = nextMessage.split(" ")[0];

                // Special case for bye to exit the program
                if (commandType.equals("bye")) {
                    wrapper(exit());
                    break;
                }
                
                switch (commandType) {
                    case "greet":
                        response = greet();
                        break;
                    case "list":
                        response = list(storedTasks);
                        break;
                    case "mark":
                        response = mark(storedTasks, nextMessage);
                        break;
                    case "unmark":
                        response = unmark(storedTasks, nextMessage);
                        break;
                    case "todo":
                        response = addTodo(nextMessage, storedTasks);
                        break;
                    case "deadline":
                        response = addDeadline(nextMessage, storedTasks);
                        break;
                    case "event":
                        response = addEvent(nextMessage, storedTasks);
                        break;
                    default:
                        throw new MiliCommandNotFoundException("Invalid command");
                        // Which is a subclass of MiliException
                }

                

            } catch (MiliException e) {
                response = e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e2) {
                response = "Sorry! Mili didn't catch that. Empty command?";
            }
            // Print wrapped response
            wrapper(response);

            nextMessage = sc.nextLine();
        }
        sc.close();
    }

    private static void printLogo() {
        String logo = " __  __  _  _  _ \n"
                + "|  \\/  |(_)| |(_)\n"
                + "| |\\/| || || | _ \n"
                + "| |  | || || || |\n"
                + "|_|  |_||_||_||_|\n";
        System.out.println("Hello from\n" + logo);
    }


    private static void wrapper(String message) {
        System.out.println(HOR_DIV_LINE + "\n" + message + "\n" + HOR_DIV_LINE + "\n");
    }
    private static String greet() {
        return "Hello! I'm Mili \nWhat can I do for you?";
    }

    private static String addTodo(String userMessage, ArrayList<Task> tasksList) throws MiliException {
        String[] inArray = userMessage.split(" ");
        String taskName = String.join(" ",
                Arrays.copyOfRange(inArray, 1, inArray.length));
        
        if (taskName.isBlank()) {
            throw new MiliEmptyDescriptionException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(taskName);
        tasksList.add(newTask);

        String message = "Got it. I've added this task: \n";
        message += "  " + newTask.getTaskIcon() + " " + newTask + "\n";
        message += "Now you have " + tasksList.size() + " tasks in the list.";
        return message;
    }
    
    private static String addDeadline(String userMessage, ArrayList<Task> tasksList) throws MiliException {
        String[] inArray = userMessage.split(" ");
        int byIndex = Arrays.asList(inArray).indexOf("/by");

        if (byIndex == -1) {
            throw new MiliInvalidFormatException("Please use: deadline [name] /by [date]");
        }

        if (byIndex == 1) {
            throw new MiliEmptyDescriptionException("The description of a deadline cannot be empty.");
        }

        String taskName = String.join(" ",
                Arrays.copyOfRange(inArray, 1, byIndex));
        String dueDate = String.join(" ",
                Arrays.copyOfRange(inArray, byIndex + 1, inArray.length));

        if (dueDate.isBlank()) {
            throw new MiliEmptyDescriptionException("The due date of a deadline cannot be empty.");
        }

        Task newTask = new Deadline(taskName, dueDate);
        tasksList.add(newTask);

        String message = "Got it. I've added this task: \n";
        message += "  " + newTask.getTaskIcon() + " " + newTask + "\n";
        message += "Now you have " + tasksList.size() + " tasks in the list.";
        return message;   
    }
    
    private static String addEvent(String userMessage, ArrayList<Task> tasksList) throws MiliException {
        String[] inArray = userMessage.split(" ");
        int fromIndex = Arrays.asList(inArray).indexOf("/from");
        int toIndex = Arrays.asList(inArray).indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new MiliInvalidFormatException("Please use: event [name] /from [start] /to [end]");
        }

        if (fromIndex == 1) {
            throw new MiliEmptyDescriptionException("The description of an event cannot be empty.");
        }

        String taskName = String.join(" ",
                Arrays.copyOfRange(inArray, 1, fromIndex));
        String startDate = String.join(" ",
                Arrays.copyOfRange(inArray, fromIndex + 1, toIndex));
        String endDate = String.join(" ",
                Arrays.copyOfRange(inArray, toIndex+ 1, inArray.length));

        if (startDate.isBlank() || endDate.isBlank()) {
            throw new MiliEmptyDescriptionException("Both start and end dates are required.");
        }

        Task newTask = new Event(taskName, startDate, endDate);
        tasksList.add(newTask);

        String message = "Got it. I've added this task: \n";
        message += "  " + newTask.getTaskIcon() + " " + newTask + "\n";
        message += "Now you have " + tasksList.size() + " tasks in the list.";
        return message;    
    }
    
    public static String mark(ArrayList<Task> tasksList, String userMessage) throws MiliException {
        try {
            int idx = Integer.parseInt(userMessage.split(" ")[1]);
            Task markedTask = tasksList.remove(idx - 1).mark();
            tasksList.add(idx - 1, markedTask);

            String acknowledgement = "Nice! I've marked this task as done:";
            String description = "  " + markedTask.getTaskIcon() + " " + markedTask;
            return acknowledgement + "\n" + description;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MiliException("Which task do you want to mark?");
        } catch (NumberFormatException e1) {
            throw new MiliException("Must provide an index in number");
        } catch (IndexOutOfBoundsException e2) {
            throw new MiliException("Invalid index given (out of scope)");
        }        
    }

    public static String unmark(ArrayList<Task> tasksList, String userMessage) throws MiliException {
        try {
            int idx = Integer.parseInt(userMessage.split(" ")[1]);
            Task unmarkedTask = tasksList.remove(idx - 1).unmark();
            tasksList.add(idx - 1, unmarkedTask);

            String acknowledgement = "OK, I've unmarked this task as not done yet:";
            String description = "  " + unmarkedTask.getTaskIcon() + " " + unmarkedTask;
            return acknowledgement + "\n" + description;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MiliException("Which task do you want to unmark?");
        } catch (NumberFormatException e1) {
            throw new MiliException("Must provide an index in number");
        } catch (IndexOutOfBoundsException e2) {
            throw new MiliException("Invalid index given (out of scope)");
        }
    }

    private static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    private static String list(ArrayList<Task> tasksList) {
        String message = "Here are your list of tasks: \n";
        int numberOfTasks = tasksList.size();
        Task curTask;
        for (int i = 0; i < numberOfTasks; i+=1) {
            curTask = tasksList.get(i);
            message += (i+1) + "." + curTask.getTaskIcon() + " " + curTask + "\n";
        }
        // rid the message of trailing \n
        message = message.trim();
        return message;
    }
}