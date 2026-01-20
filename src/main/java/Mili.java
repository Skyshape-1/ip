import java.util.Scanner;

public class Mili {
    public static void main(String[] args) {
        String HOR_DIV_LINE = "------------------------------------------------";

        printLogo();
        greet(HOR_DIV_LINE);

        Scanner sc = new Scanner(System.in);

        String nextMessage = sc.nextLine();
        while(true) {
            if (nextMessage.equals("bye")) {
                exit(HOR_DIV_LINE);
                break;
            }
            echo(nextMessage, HOR_DIV_LINE);
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

    private static void greet(String hor_div_line) {
        String greeting = "Hello! I'm Mili \nWhat can I do for you?\n";
        System.out.println(hor_div_line);
        System.out.println(greeting);
        System.out.println(hor_div_line);
        System.out.println("\n");
    }

    private static void echo(String userMessage, String hor_div_line) {
        System.out.println(hor_div_line);
        System.out.println(userMessage);
        System.out.println(hor_div_line);
        System.out.println("\n");
    }

    private static void exit(String hor_div_line) {
        String byebye = "Bye. Hope to see you again soon!";
        System.out.println(hor_div_line);
        System.out.println(byebye);
        System.out.println(hor_div_line);
        System.out.println("\n");
    }
}