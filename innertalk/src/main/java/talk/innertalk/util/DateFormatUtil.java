package talk.innertalk.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatUtil {

    public static String DateFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return dateTime.format(formatter);
    }
}
