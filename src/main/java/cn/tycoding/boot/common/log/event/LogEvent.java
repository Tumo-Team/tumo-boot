package cn.tycoding.boot.common.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义定义 Log事件
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {
        super(source);
    }
}
