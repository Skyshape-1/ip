public class Mili {
    public static void main(String[] args) {
        // Your specific variable name
        String HOR_DIV_LINE = "------------------------------------------------";

        printLogo();
        greet(HOR_DIV_LINE);

        // This exit call is for Level-0.
        // In Level-1, you will wrap this in a loop.
        exit(HOR_DIV_LINE);
    }

    private static void printLogo() {
        String logo = " __  __  _  _  _ \n"
                + "|  \\/  |(_)| |(_)\n"
                + "| |\\/| || || | _ \n"
                + "| |  | || || || |\n"
                + "|_|  |_||_||_||_|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet(String hor_div_line) {
        // Your specific variable name
        String greeting = "Hello! I'm Mili \nWhat can I do for you?\n";
        System.out.println(hor_div_line);
        System.out.println(greeting);
        System.out.println(hor_div_line);
    }

    public static void exit(String hor_div_line) {
        // Your specific variable name
        String byebye = "Bye. Hope to see you again soon!";
        System.out.println(hor_div_line);
        System.out.println(byebye);
        System.out.println(hor_div_line);
    }
}