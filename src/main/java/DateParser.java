import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    private static final DateTimeFormatter INPUT_FORMATTER_1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER_2 = DateTimeFormatter.ofPattern("dd/M/yyyy HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER_3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER_4 = DateTimeFormatter.ofPattern("dd/M/yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER_5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_FORMATTER_6 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private static final String DATE_FORMAT_ERROR_MESSAGE = "Please use one of the following formats:\ndd/MM/yyyy HHmm\ndd/M/yyyy HHmm\ndd/MM/yyyy\ndd/M/yyyy";

    public static LocalDate parse(String dateString) throws MiliDateFormatException {
        try {
            // Memory date parser
            if (dateString.matches("^\\d{4}-\\d{2}-\\d{2} \\d{4}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_6);
            } else if (dateString.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_5);
            } 
            
            // User input date parser
            if (dateString.matches("^\\d{2}/\\d{2}/\\d{4} \\d{4}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_1);
            } else if (dateString.matches("^\\d{2}/\\d{1}/\\d{4} \\d{4}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_2);
            } else if (dateString.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_3);
            } else if (dateString.matches("^\\d{2}/\\d{1}/\\d{4}$")) {
                return LocalDate.parse(dateString, INPUT_FORMATTER_4);
            } else {
                throw new MiliDateFormatException(DATE_FORMAT_ERROR_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            throw new MiliDateFormatException(DATE_FORMAT_ERROR_MESSAGE);
        }
    }
}
