package global.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateGenerator {
    private static final Random RANDOM = new Random();
    private static final int YEAR = 2025;
    private DateGenerator(){};
    public static Date getRandomDateInMarch() {
        int dayOfMarch = RANDOM.nextInt(31) + 1;  // 1 ~ 31
        return new Date(YEAR - 1900, Calendar.APRIL, dayOfMarch);  // 2 = 3월 (0-based)
    }
}
