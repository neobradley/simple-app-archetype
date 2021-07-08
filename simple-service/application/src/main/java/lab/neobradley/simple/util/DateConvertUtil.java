package lab.neobradley.simple.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Slf4j
public class DateConvertUtil {

    //日期格式
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String START_OF_DAY = " 00:00:00";
    public static final String END_OF_DAY = " 23:59:59";
    public static final String STAT_DAY_FORMAT = "yyyyMMdd";

//    public static final long ONE_HOUR_MILLISECONDS = 60 * 60 * 1000;
//    public static final int TIMEZONE_OFFSET = 0;

    public static List<Map<Date, Date>> convertDateRangeToDailyRange(String fromDate, String toDate) {
        List<Map<Date, Date>> dailyRange = new ArrayList<>(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
        LocalDateTime from = LocalDateTime.parse(fromDate.contains(" ") ? fromDate : fromDate + START_OF_DAY, formatter);
        LocalDateTime to = LocalDateTime.parse(toDate.contains(" ") ? toDate : toDate + END_OF_DAY, formatter);
        LocalDateTime cut = from.toLocalDate().plusDays(1).atStartOfDay().minusSeconds(1);
        while (cut.isBefore(to.toLocalDate().atStartOfDay())) {
            dailyRange.add(Collections.singletonMap(Date.from(from.toInstant(zoneOffset)), Date.from(cut.toInstant(zoneOffset))));
            from = from.toLocalDate().atStartOfDay().plusDays(1);
            cut = cut.plusDays(1);
        }
        if (cut.minusDays(1).isBefore(to)) {
            Date fromInstant = Date.from(fromDate.contains(" ")
                ? cut.minusDays(1).plusSeconds(1).toInstant(zoneOffset)
                : from.toInstant(zoneOffset));
            dailyRange.add(Collections.singletonMap(fromInstant, Date.from(to.toInstant(zoneOffset))));
        }
        return dailyRange;
    }

    public static LocalDateTime parseDateTime(Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate parseDate(Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static Date parseDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDateTime = LocalDate.parse(date, formatter);
        return toBeginDate(localDateTime);
    }

    public static Date convertDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime)));
    }

    public static Date convertDate(Date dateTime) {
        return convertDate(parseDateTime(dateTime).toLocalDate().atStartOfDay());
    }

    public static Date convertDateHourBegin(Date dateTime) {
        return convertDate(parseDateTime(dateTime).withMinute(0).withSecond(0).withNano(0));
    }

    public static Date convertDateHourEnd(Date dateTime) {
        return convertDate(parseDateTime(dateTime).withMinute(59).withSecond(59));
    }

    public static LocalDateTime toBegin(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    public static LocalDateTime toBegin(Date date) {
        return toBegin(parseDate(date));
    }

    public static Date toBeginDate(LocalDate localDate) {
        return convertDate(toBegin(localDate));
    }

    public static LocalDateTime toEnd(LocalDate localDate) {
        return localDate.plusDays(1).atStartOfDay().minusSeconds(1);
    }

    public static LocalDateTime toEnd(Date date) {
        return toEnd(parseDate(date));
    }

    public static Date toEndDate(LocalDate localDate) {
        return convertDate(toEnd(localDate));
    }

    /**
     * 获取24 小时段的时间
     *
     * @return public static List<Map<String, String>> getDateInterval(Date beginDate, Date endDate) {
    String beginTime = DateFormatUtils.format(beginDate, DATE_TIME_FORMAT);
    String endTime = DateFormatUtils.format(endDate, "yyyy-MM-dd 23:59:59");
    List<String> list = cutDate("D", beginTime, endTime);
    List<Map<String, String>> mapList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
    String time = list.get(i);
    Date date = parseDateFormat(time, DATE_TIME_FORMAT);
    Date hourBeginTime = null;
    Date hourEndTime = null;
    if (i == list.size() - 1) {
    hourBeginTime = addToDate(addToDate(date, Calendar.DAY_OF_YEAR, -1), Calendar.SECOND, 1);
    hourEndTime = date;
    } else {
    hourBeginTime = addToDate(date, Calendar.DAY_OF_YEAR, -1);
    hourEndTime = addToDate(date, Calendar.SECOND, -1);
    }
    Map<String, String> map = new HashMap<>();
    map.put(getDateFormat(hourBeginTime, DATE_TIME_FORMAT), getDateFormat(hourEndTime, DATE_TIME_FORMAT));
    mapList.add(map);

    }
    return mapList;

    }

    public static Date addToDate(Date date, Integer field, Integer value) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(field, value);

    return toTimestamp(calendar);
    }

    public static final Timestamp toTimestamp(Calendar calendar) {
    return toTimestamp(calendar, ONE_HOUR_MILLISECONDS);
    }

    public static final Timestamp toTimestamp(Calendar calendar,
    long millisecondsInAnHour) {
    return new Timestamp(calendar.getTimeInMillis() + TIMEZONE_OFFSET
     * millisecondsInAnHour);
    }
     */

    /**
     * 切割時間段
     *
     * @param dateType 交易類型 M/D/H/N -->每月/每天/每小時/每分鐘
     * @param start    yyyy-MM-dd HH:mm:ss
     * @param end      yyyy-MM-dd HH:mm:ss
     * @return public static List<String> cutDate(String dateType, String start, String end) {
    try {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dBegin = sdf.parse(start);
    Date dEnd = sdf.parse(end);
    return findDates(dateType, dBegin, dEnd);
    } catch (Exception e) {
    log.error("cutDate 执行失败", e);
    }
    return null;
    }

    public static List<String> findDates(String dateType, Date dBegin, Date dEnd) throws Exception {
    List<String> listDate = new ArrayList<>();
    Calendar calBegin = Calendar.getInstance();
    calBegin.setTime(dBegin);
    Calendar calEnd = Calendar.getInstance();
    calEnd.setTime(dEnd);
    while (calEnd.after(calBegin)) {
    switch (dateType) {
    case "M":
    calBegin.add(Calendar.MONTH, 1);
    break;
    case "D":
    calBegin.add(Calendar.DAY_OF_YEAR, 1);
    break;
    case "H":
    calBegin.add(Calendar.HOUR, 1);
    break;
    case "N":
    calBegin.add(Calendar.SECOND, 1);
    break;
    default:
    log.error("dateType 不合法 {}", dateType);
    break;
    }
    if (calEnd.after(calBegin))
    listDate.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calBegin.getTime()));
    else
    listDate.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calEnd.getTime()));
    }
    return listDate;
    }
     */

    /**
     * @param strDate
     * @param pattern
     * @return java.util.Date
     * public static Date parseDateFormat(String strDate, String pattern) {
     * Date date = null;
     * SimpleDateFormat sdf = new SimpleDateFormat();
     * sdf.applyPattern(pattern);
     * try {
     * date = sdf.parse(strDate);
     * } catch (Exception e) {
     * log.error("时间转换错误strDate {},pattern{}", strDate, pattern);
     * }
     * return date;
     * }
     * <p>
     * public static String getDateFormat(Date date, String dateFormat) {
     * if (date == null || StringUtils.isEmpty(dateFormat)) return "";
     * SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
     * return sdf.format(date);
     * }
     */

}
