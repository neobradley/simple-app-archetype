package lab.neobradley.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AppTest {

    @BeforeEach
    void init() {
        log.info("===== TEST BEGIN =====");
    }

    @AfterEach
    void cleanup() {
        log.info("===== TEST ENDED =====");
    }

    @Test
    void contextLoaded() {
        log.info("contextLoaded");
    }
}
