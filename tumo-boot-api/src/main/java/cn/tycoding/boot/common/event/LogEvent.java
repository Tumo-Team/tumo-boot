package cn.tycoding.boot.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author tycoding
 * @since 2020/10/31
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {
        super(source);
    }
}
