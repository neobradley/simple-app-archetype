package lab.neobradley.simple.application.config.ds;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切换处理器
 */
@Slf4j
@Aspect
@Order(-1)  // 该切面应当先于 @Transactional 执行
@Component
public class DynamicDataSourceAspect {

    /**
     * 切换数据源
     *
     * @param point
     * @param dataSource
     */
    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint point, DataSource dataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())) {
            log.error("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        } else {
            // 切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
            log.info("Switch DataSource to {} in Method {}", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    /**
     * 重置数据源
     *
     * @param point
     * @param dataSource
     @After("@annotation(dataSource))") public void restoreDataSource(JoinPoint point, DataSource dataSource) {
     // 将数据源置为默认数据源
     DynamicDataSourceContextHolder.clearDataSourceKey();
     log.info("Restore DataSource to {} in Method {}", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
     }
     */
}
