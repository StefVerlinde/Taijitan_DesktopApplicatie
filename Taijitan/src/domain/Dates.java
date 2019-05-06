package domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Dates {

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static boolean sameDay(Date date1,Date date2){
        return !date1.before(date2) && !date1.after(date2);
    }
}
