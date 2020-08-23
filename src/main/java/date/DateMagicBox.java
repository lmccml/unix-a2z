package date;

import model.DateTimeResult;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lmc
 * @date 2020/8/22 17:12
 */
public class DateMagicBox {
    public static void main(String[] args) {
        System.out.println(DateMagicBox.getDateByAnyDays(-2));
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /*
     *获取当前日期
     */
    public static String now() {
        LocalDate localDate = LocalDate.now();
        String now = localDate.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取某时区的当前日期
     */
    public static String nowWithZoneId(String zoneOffset) {
        LocalDate localDate = LocalDate.now(ZoneOffset.of(zoneOffset));
        String now = localDate.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本年第一天
     */
    public static String yearOfFirstDay() {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        String now = localDate.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本月第一天
     */
    public static String monthOfFirstDay() {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        String now = localDate.format(dateTimeFormatter);
        return now;
    }

    /*
     *获取本月最后一天
     */
    public static String monthOfEndDay() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        String now = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return now;
    }

    /*
    获取近七天
     */
    public static DateTimeResult lastSevenDay() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDate localDateStartTime = LocalDate.now().plusDays(-7);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeResult.setStartTime(startTime);

        LocalDate localDateEndTime = LocalDate.now().plusDays(-1);
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }

    /*
    获取近3月
    */
    public static DateTimeResult lastThreeMonth() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDate localDateStartTime = LocalDate.now().plusMonths(-3);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-01"));
        dateTimeResult.setStartTime(startTime);

        LocalDate localDateEndTime = LocalDate.now().plusMonths(-1).with(TemporalAdjusters.lastDayOfMonth());
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }

    /*
    获取近6月
    */
    public static DateTimeResult lastSixMonth() {
        DateTimeResult dateTimeResult = new DateTimeResult();

        LocalDate localDateStartTime = LocalDate.now().plusMonths(-6);
        String startTime = localDateStartTime.format(DateTimeFormatter.ofPattern("yyyy-MM-01"));
        dateTimeResult.setStartTime(startTime);

        LocalDate localDateEndTime = LocalDate.now().plusMonths(-1).with(TemporalAdjusters.lastDayOfMonth());
        String endTime = localDateEndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeResult.setEndTime(endTime);

        return dateTimeResult;
    }


    /*
    获取今年过去多少天
    */
    public static Integer dayOfYear() {
        return LocalDate.now().getDayOfYear();
    }

    /*
    获取今年还剩多少天
    */
    public static Integer dayToNextYear() {
        Integer dayOfYear = LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).getDayOfYear();
        return dayOfYear - LocalDateTime.now().getDayOfYear();
    }

    /*
    获取指定天数的日期
     */
    public static String getDateByAnyDays(Integer days) {
        Integer dayOfYear = LocalDate.now().with(TemporalAdjusters.lastDayOfYear()).getDayOfYear();
        LocalDate localDate = LocalDate.now().plusDays(days);
        return localDate.format(dateTimeFormatter);
    }






}
