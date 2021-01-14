package pers.ycm.sbdefault.service.designpattern.strategypattern;

import org.springframework.stereotype.Component;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
@Component("StrategyB")
public class StrategyB implements Strategy {
    @Override
    public String handle(StrategyParam param) {
        return "StrategyB";
    }
}
