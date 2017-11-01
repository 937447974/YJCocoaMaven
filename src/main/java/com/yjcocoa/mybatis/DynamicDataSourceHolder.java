package com.yjcocoa.mybatis;

import com.yjcocoa.common.Lambda;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * <p>Title: DynamicDataSourceHolder</p>
 * <p>Description: 数据源持有</p>
 * <p>HomePage: https://github.com/937447974/YJCocoaMaven</p>
 * <p>YJ技术支持群: 557445088</p>
 * <p>Create Time: 2017/10/30 下午1:14</p>
 * <p>Copyright: © 2017年 yjcocoa. All rights reserved.</p>
 *
 * @author 阳君
 * @since 17.11.1
 */
@Component
public class DynamicDataSourceHolder {

    protected static DataSourceTransactionManager transactionManager;
    private static final ThreadLocal<Boolean> threadLocal = new ThreadLocal();

    /**
     * 获取是否使用主库
     *
     * @return Boolean
     */
    public static Boolean getMaster() {
        return threadLocal.get() != null;
    }

    /**
     * 切换主从库，自带事务管理
     *
     * @param master 是否使用主库
     * @param lambda 业务调用
     * @throws Throwable
     */
    public static void setMaster(Boolean master, Lambda lambda) throws Exception {
        if (master) {
            threadLocal.set(true);
        } else {
            threadLocal.remove();
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            if (lambda != null) {
                lambda.invoke();
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        } finally {
            lambda = null;
        }
    }

}