public class MiliException extends Exception {
    public MiliException(String message) {
        super("Mili: OOPS! " + message);
    }
}