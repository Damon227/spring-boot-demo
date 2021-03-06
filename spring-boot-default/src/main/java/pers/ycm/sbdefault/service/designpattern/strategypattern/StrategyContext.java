package pers.ycm.sbdefault.service.designpattern.strategypattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.ycm.sbdefault.common.exception.BizException;
import pers.ycm.sbdefault.common.enums.CodeEnum;

import java.util.Map;
import java.util.Optional;

/**
 * @author yuanchengman
 * @date 2021-01-14
 */
@Component
public class StrategyContext {
    @Autowired
    private Map<String, Strategy> context;

    public Strategy getInstance(String strategyName) {
        if (CollectionUtils.isEmpty(context)) {
            throw new BizException(CodeEnum.BIZ_STRATEGY_EMPTY);
        }

        return Optional.ofNullable(context.get(strategyName))
                .orElseThrow(() -> new BizException(CodeEnum.BIZ_STRATEGY_NAME_NOT_EXIST));
    }
}
