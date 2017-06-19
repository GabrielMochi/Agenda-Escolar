package agendaescolar.service;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Gabriel Mochi
 */
public class Time {
    
    public static DateTimeFormatter DatePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static LocalDate formatToLocalDatePattern(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DatePattern);
    }
    
    public static String formatToBrazilianPattern(LocalDate date) throws DateTimeException {
        return date.format(DatePattern);
    }
    
}
