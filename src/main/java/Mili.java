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
            newTask = new Task(nextMessage);
            echo(nextMessage, storedTasks);
            storedTasks.add(newTask);
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

        System.out.println(HOR_DIV_LINE);
        System.out.println("added: " + userMessage);
        System.out.println(HOR_DIV_LINE);
        System.out.println("\n");
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
        for (int i = 1; i <= numberOfTasks; i+=1) {
            System.out.println(i + ". " + tasksList.get(i));
        }
        System.out.println(HOR_DIV_LINE);
    }
}