package pers.ycm.sbdefault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuanchengman
 * @date 2021-01-26
 */
@SpringBootTest(classes = ServiceInitializer.class)
@RunWith(SpringRunner.class)
public class LogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testInfo(){
        LOGGER.info("123");
    }

    @Test
    public void testWarn(){
        LOGGER.warn("456");
    }

    @Test
    public void testError(){
        LOGGER.error("789");
    }

    @Test
    public void testBatchError(){
        int max = 10000;
        for (int i = 0; i < max; i++) {
            LOGGER.error("789");
        }
    }
}
