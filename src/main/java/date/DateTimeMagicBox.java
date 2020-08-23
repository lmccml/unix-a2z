package date;

import model.DateTimeResult;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lmc
 * @date 2020/8/22 17:12
 */
public class DateTimeMagicBox {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println(DateMagicBox.getDateByAnyDays(-2));
    }

    /*
    时间戳转换成时间
    */
    public static String dateTimeFromStamp(Long timeStamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
    时间戳转换成时间(带时区)
    * @param zoneOffset 时区  +8
    */
    public static String dateTimeFromStampWithZoneId(Long timeStamp, String zoneOffset) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneOffset.of(zoneOffset));
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /**
     * 获取系统当前时间戳
     */
    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取今日零点时间戳
     */
    public static Long getZeroTimestamp() {
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0, 0);
        return localDateTime.toInstant(zoneOffset).toEpochMilli();
    }

    /*
     *获取当前时间
     */
    public static String now() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取某时区的当前时间
     */
    public static String nowWithZoneId(String zoneOffset) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.of(zoneOffset));
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本年第一天
     */
    public static String yearOfFirstDay() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 0, 0, 0);
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本月第一天
     */
    public static String monthOfFirstDay() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1, 0, 0, 0);
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本月最后一天
     */
    public static String monthOfEndDay() {
        LocalDateTime localDateTime = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        String now = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
        return now;
    }

    /*
     *获取当天零点
     */
    public static String zeroOfDay() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 0, 0, 0);
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }


    /*
     *获取当天结束
     */
    public static String endOfDay() {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 23, 59, 59);
        String now = localDateTime.format(dateTimeFormatter);
        return now;
    }

    /*
    获取近七天
     */
    public static DateTimeResult lastSevenDay() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDateTime localDateStartTime = LocalDateTime.now().plusDays(-7);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        dateTimeResult.setStartTime(startTime);

        LocalDateTime localDateEndTime = LocalDateTime.now().plusDays(-1);
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }

    /*
    获取近3月
    */
    public static DateTimeResult lastThreeMonth() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDateTime localDateStartTime = LocalDateTime.now().plusMonths(-3);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-01 00:00:00"));
        dateTimeResult.setStartTime(startTime);

        LocalDateTime localDateEndTime = LocalDateTime.now().plusMonths(-1).with(TemporalAdjusters.lastDayOfMonth());
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }

    /*
    获取近6月
    */
    public static DateTimeResult lastSixMonth() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDateTime localDateStartTime = LocalDateTime.now().plusMonths(-6);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-01 00:00:00"));
        dateTimeResult.setStartTime(startTime);

        LocalDateTime localDateEndTime = LocalDateTime.now().plusMonths(-1).with(TemporalAdjusters.lastDayOfMonth());
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }
}
