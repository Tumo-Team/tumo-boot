package cn.tycoding.boot.common.log.event;

import cn.tycoding.boot.modules.resource.entity.SysLog;
import cn.tycoding.boot.modules.resource.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听自定义Log 事件
 *
 * @author tycoding
 * @since 2021/5/21
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
