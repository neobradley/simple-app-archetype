package lab.neobradley.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AppTest {

    @BeforeEach
    public void init() {
        log.info("===== TEST BEGIN =====");
    }

    @AfterEach
    public void cleanup() {
        log.info("===== TEST ENDED =====");
    }

    @Test
    public void contextLoaded() {
        log.info("contextLoaded");
    }
}
