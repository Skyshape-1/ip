import java.util.Scanner;
import java.util.ArrayList;

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


        Task newTask = new Task(userMessage);
        tasksList.add(newTask);

        System.out.println(HOR_DIV_LINE);
        System.out.println("added: " + userMessage);
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
            System.out.println(markedTask.getStatusIcon() + " " + markedTask);
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
            System.out.println(unmarkedTask.getStatusIcon() + " " + unmarkedTask);
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
        int numberOfTasks = tasksList.size();
        Task curTask;
        for (int i = 0; i < numberOfTasks; i+=1) {
            curTask = tasksList.get(i);
            System.out.println((i+1) + "." + curTask.getStatusIcon() + " " + curTask);
        }
        System.out.println(HOR_DIV_LINE);
    }
}