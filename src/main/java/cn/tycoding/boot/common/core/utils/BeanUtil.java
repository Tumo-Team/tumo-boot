package cn.tycoding.boot.common.core.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Orika Bean拷贝工具的简单封装
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class BeanUtil {

    private static final MapperFacade MAPPER;

    static {
        DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();
        MAPPER = factory.getMapperFacade();
    }

    public static <T> T copy(Object source, T target) {
        if (source == null) {
            return null;
        }
        MAPPER.map(source, target);
        return target;
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return MAPPER.map(source, targetClass);
    }

    public static <T> List<T> copy(Collection<?> source, Class<T> targetClass) {
        if (source != null && source.size() > 0) {
            return MAPPER.mapAsList(source, targetClass);
        } else {
            return Collections.emptyList();
        }
    }
}
