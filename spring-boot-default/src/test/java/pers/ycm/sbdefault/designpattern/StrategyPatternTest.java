package pers.ycm.sbdefault.designpattern;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.ycm.sbdefault.ServiceInitializer;
import pers.ycm.sbdefault.service.designpattern.strategypattern.StrategyContext;
import pers.ycm.sbdefault.service.designpattern.strategypattern.StrategyParam;

/**
 * 策略模式单元测试
 *
 * @author yuanchengman
 * @date 2021-01-14
 */
@SpringBootTest(classes = ServiceInitializer.class)
@RunWith(SpringRunner.class)
public class StrategyPatternTest {
    @Autowired
    private StrategyContext context;

    @Test
    public void test1() {
        String strategyName = "StrategyA";
        StrategyParam param = null;
        String handleResult = context.getInstance(strategyName).handle(param);
        Assert.assertEquals(strategyName, handleResult);
    }

    @Test
    public void test2() {
        String strategyName = "StrategyA";
        StrategyParam param = null;
        String handleResult = context.getInstance(strategyName).handle(param);
        Assert.assertNotEquals("StrategyB", handleResult);
    }
}
