package cn.tycoding.boot.common.log.event;

import cn.tycoding.boot.modules.system.entity.SysLog;
import cn.tycoding.boot.modules.system.service.SysLogService;
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

    private final SysLogService sysLogService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveLog(LogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        sysLogService.add(sysLog);
    }
}
