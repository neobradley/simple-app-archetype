package lab.neobradley.simple.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class DateConvertUtilTest {

//    @Test
//    public void getDateInterval() {
//        ZoneOffset zoneOffset = OffsetDateTime.now().getOffset();
//        LocalDateTime minusDays = LocalDateTime.now().minusDays(3).minusMinutes(5);
//        List<Map<String, String>> dateInterval = DateConvertUtil.getDateInterval(Date.from(minusDays.atOffset(zoneOffset).toInstant()), Date.from(LocalDateTime.now().atOffset(zoneOffset).toInstant()));
//        dateInterval.forEach(map -> map.entrySet().forEach(entry -> {
//            log.info("getDateInterval {} - {}", entry.getKey(), entry.getValue());
//        }));
//    }

    @Test
    public void convertDateRangeToDailyRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime minusDays = LocalDateTime.now().minusDays(3).minusMinutes(5);
        List<Map<Date, Date>> dateInterval = DateConvertUtil.convertDateRangeToDailyRange(formatter.format(minusDays), formatter.format(LocalDateTime.now()));
        dateInterval.forEach(map -> map.entrySet().forEach(entry -> {
            log.info("convertDateRangeToDailyRange {} - {}", entry.getKey(), entry.getValue());
        }));
    }

    @Test
    public void convertDateRangeToDailyRangeWithTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConvertUtil.DATE_TIME_FORMAT);
        LocalDateTime minusDays = LocalDateTime.now().minusDays(3).minusMinutes(5);
        List<Map<Date, Date>> dateInterval = DateConvertUtil.convertDateRangeToDailyRange(formatter.format(minusDays), formatter.format(LocalDateTime.now()));
        dateInterval.forEach(map -> map.entrySet().forEach(entry -> {
            log.info("convertDateRangeToDailyRangeWithTime {} - {}", entry.getKey(), entry.getValue());
        }));
    }
}
