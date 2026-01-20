import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mili {
    private static final String HOR_DIV_LINE = "------------------------------------------------";
    public static void main(String[] args) {

        ArrayList<Task> storedTasks = new ArrayList<Task>(100);

        printLogo();
        greet();

        Scanner sc = new Scanner(System.in);

        String nextMessage = sc.nextLine();
        Task newTask;
        while(true) {
            if (nextMessage.equals("bye")) {
                exit();
                break;
            }
            // newTask = new Task(nextMessage);
            echo(nextMessage, storedTasks);
            // storedTasks.add(newTask);
            nextMessage = sc.nextLine();

        }
    }

    private static void printLogo() {
        String logo = " __  __  _  _  _ \n"
                + "|  \\/  |(_)| |(_)\n"
                + "| |\\/| || || | _ \n"
                + "| |  | || || || |\n"
                + "|_|  |_||_||_||_|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void greet() {
        String greeting = "Hello! I'm Mili \nWhat can I do for you?\n";
        System.out.println(HOR_DIV_LINE);
        System.out.println(greeting);
        System.out.println(HOR_DIV_LINE);
        System.out.println("\n");
    }

    private static void echo(String userMessage, ArrayList<Task> tasksList) {

        if (userMessage.equals("list")) {
            list(tasksList);
            return;
        }

        if (userMessage.contains("unmark")) {
            unmark(tasksList, userMessage.split(" ")[1]);
            return;
        } else if (userMessage.contains("mark")) {
            mark(tasksList, userMessage.split(" ")[1]);
            return;
        }


        // Logic: Handle adding a new task
        String taskTypeLong, taskName, taskTypeShort;
        Task newTask;

        String[] inArray = userMessage.split(" ");
        taskTypeLong = inArray[0];


        if (taskTypeLong.equals("todo")) {
            taskTypeShort = "T";

            taskName = String.join(" ",
                    Arrays.copyOfRange(inArray, 1, inArray.length));
            newTask = new Todo(taskName);

        } else if (taskTypeLong.equals("deadline")) {
            taskTypeShort = "D";

            int byIndex = Arrays.asList(inArray).indexOf("/by");
            taskName = String.join(" ",
                    Arrays.copyOfRange(inArray, 1, byIndex));
            String dueDate = String.join(" ",
                    Arrays.copyOfRange(inArray, byIndex + 1, inArray.length));

            newTask = new Deadline(taskName, dueDate);

        } else if (taskTypeLong.equals("event")) {
            taskTypeShort = "E";

            int fromIndex = Arrays.asList(inArray).indexOf("/from");
            int toIndex = Arrays.asList(inArray).indexOf("/to");
            taskName = String.join(" ",
                    Arrays.copyOfRange(inArray, 1, fromIndex));
            String startDate = String.join(" ",
                    Arrays.copyOfRange(inArray,  fromIndex + 1, toIndex));
            String endDate = String.join(" ",
                    Arrays.copyOfRange(inArray,  toIndex+ 1, inArray.length));

            newTask = new Event(taskName, startDate, endDate);
        } else {
            System.out.println("Invalid or unspecified task type. adding rejected!");
            return;
        }


        // Task newTask = new Task(); Implement subclasses of Task class!!!
        tasksList.add(newTask);

        System.out.println(HOR_DIV_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.getTaskIcon() + " " + newTask);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        System.out.println(HOR_DIV_LINE);

        System.out.println("\n");
    }

    public static void mark(ArrayList<Task> tasksList, String index) {
        try {
            int idx = Integer.parseInt(index);
            Task markedTask = tasksList.remove(idx - 1).mark();
            tasksList.add(idx - 1, markedTask);

            // Print messages
            System.out.println(HOR_DIV_LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(markedTask.getTaskIcon() + " " + markedTask);
            System.out.println(HOR_DIV_LINE);
            System.out.println("\n");
        } catch (NumberFormatException e) {
            System.out.println("Must provide an index in number");
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Invalid index given (out of scope)");
        }
    }

    public static void unmark(ArrayList<Task> tasksList, String index) {
        try {
            int idx = Integer.parseInt(index);
            Task unmarkedTask = tasksList.remove(idx - 1).unmark();
            tasksList.add(idx - 1, unmarkedTask);

            // Print messages
            System.out.println(HOR_DIV_LINE);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(unmarkedTask.getTaskIcon() + " " + unmarkedTask);
            System.out.println(HOR_DIV_LINE);
            System.out.println("\n");
        } catch (NumberFormatException e1) {
            System.out.println("Must provide an index in number");
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Invalid index given (out of scope)");
        }
    }

    private static void exit() {
        String byebye = "Bye. Hope to see you again soon!";
        System.out.println(HOR_DIV_LINE);
        System.out.println(byebye);
        System.out.println(HOR_DIV_LINE);
        System.out.println("\n");
    }

    private static void list(ArrayList<Task> tasksList) {
        System.out.println(HOR_DIV_LINE);
        System.out.println("Here are your list of tasks: ");
        int numberOfTasks = tasksList.size();
        Task curTask;
        for (int i = 0; i < numberOfTasks; i+=1) {
            curTask = tasksList.get(i);
            System.out.println((i+1) + "." + curTask.getTaskIcon() + " " + curTask);
        }
        System.out.println(HOR_DIV_LINE);
    }
}