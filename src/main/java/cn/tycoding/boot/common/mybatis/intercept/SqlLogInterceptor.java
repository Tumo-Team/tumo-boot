package cn.tycoding.boot.common.mybatis.intercept;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Statement;

/**
 * @author tycoding
 * @since 2020/11/10
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class}
)})
public class SqlLogInterceptor implements Interceptor {

    private Method druidGetSqlMethod;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object firstArg = invocation.getArgs()[0];
        Statement statement;
        if (Proxy.isProxyClass(firstArg.getClass())) {
            statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
        } else {
            statement = (Statement) firstArg;
        }

        String originalSql = null;
        String stmtClassName = statement.getClass().getName();
        Class clazz;
        Object stmtSql;
        if ("com.alibaba.druid.pool.DruidPooledPreparedStatement".equals(stmtClassName)) {
            try {
                if (this.druidGetSqlMethod == null) {
                    clazz = Class.forName("com.alibaba.druid.pool.DruidPooledPreparedStatement");
                    this.druidGetSqlMethod = clazz.getMethod("getSql");
                }

                stmtSql = this.druidGetSqlMethod.invoke(statement);
                if (stmtSql instanceof String) {
                    originalSql = (String) stmtSql;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (originalSql == null) {
            originalSql = statement.toString();
        }

        long start = SystemClock.now();
        Object result = invocation.proceed();
        long timing = SystemClock.now() - start;
        Object target = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(target);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        System.err.println(StrFormatter.format("\n==============  Sql Start  ==============\nExecute ID  ：{}\nExecute SQL ：{}\nExecute Time：{} ms\n==============  Sql  End   ==============\n", ms.getId(), StrUtil.replaceChars(originalSql, "\n", " "), timing));
        return result;
    }
}
