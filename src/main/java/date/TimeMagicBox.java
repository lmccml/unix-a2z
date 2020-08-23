package date;

import model.DateTimeResult;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lmc
 * @date 2020/8/22 17:12
 */
public class TimeMagicBox {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        System.out.println(DateMagicBox.getDateByAnyDays(-2));
    }

    /*
    获取近24小时
     */
    public static DateTimeResult lastTwentyFourHours() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalTime localStartTime = LocalTime.now().plusHours(-24);
        String startTime = localStartTime.format(dateTimeFormatter);
        dateTimeResult.setStartTime(startTime);

        LocalTime localEndTime = LocalTime.now();
        String endTime = localEndTime.format(dateTimeFormatter);
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }

    /*
    获取近72小时
     */
    public static DateTimeResult lastThreeDays() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalTime localStartTime = LocalTime.now().plusHours(-72);
        String startTime = localStartTime.format(dateTimeFormatter);
        dateTimeResult.setStartTime(startTime);

        LocalTime localEndTime = LocalTime.now();
        String endTime = localEndTime.format(dateTimeFormatter);
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }





}
