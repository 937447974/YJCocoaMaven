package com.yjcocoa.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * <p>Title: DynamicDataSourceInterceptor</p>
 * <p>Description: 数据源拦截器</p>
 * <p>HomePage: https://github.com/937447974/YJCocoaMaven</p>
 * <p>YJ技术支持群: 557445088</p>
 * <p>Create Time: 2017/10/30 下午12:01</p>
 * <p>Copyright © 2017年 yjcocoa. All rights reserved.</p>
 *
 * @author 阳君
 * @since
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        if (!DynamicDataSourceHolder.getMaster()) {
            String error = "### YJCocoa Error: updating database must use masterDataSource";
            System.out.println(error);
            throw new IllegalAccessException(error);
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties properties) {
    }

}
