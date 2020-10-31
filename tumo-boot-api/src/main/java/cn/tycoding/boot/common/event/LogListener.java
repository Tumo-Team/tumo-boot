package cn.tycoding.boot.common.event;

import cn.tycoding.boot.modules.setting.entity.Log;
import cn.tycoding.boot.modules.setting.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author tycoding
 * @since 2020/10/31
 */
@RequiredArgsConstructor
public class LogListener {

    private final LogService logService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveLog(LogEvent event) {
        Log log = (Log) event.getSource();
        logService.add(log);
    }
}
