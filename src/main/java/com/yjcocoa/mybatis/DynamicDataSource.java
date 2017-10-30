package com.yjcocoa.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: DynamicDataSource</p>
 * <p>Description: 动态化数据源</p>
 * <p>HomePage: https://github.com/937447974/YJCocoaMaven</p>
 * <p>YJ技术支持群: 557445088</p>
 * <p>Create Time: 2017/10/30 下午12:01</p>
 * <p>Copyright © 2017年 yjcocoa. All rights reserved.</p>
 *
 * @author 阳君
 * @since
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /** 主库 */
    private DataSource masterDataSource;
    /** 从库 */
    private List<DataSource> slaveDataSources;
    /** 从库偏移量 */
    private Integer slaveIndex = 0;

    @Override
    public void afterPropertiesSet() throws IllegalArgumentException {
        if (this.masterDataSource == null || this.slaveDataSources == null) {
            throw new IllegalArgumentException("Property 'masterDataSource、slaveDataSources' is required");
        }
        this.setDefaultTargetDataSource(this.masterDataSource);
        Map<Object, Object> targetDataSources = new HashMap(this.slaveDataSources.size());
        targetDataSources.put("master", this.masterDataSource);
        for (int i = 0; i < this.slaveDataSources.size(); i++) {
            targetDataSources.put("slave-" + i, this.slaveDataSources.get(i));
        }
        this.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (DynamicDataSourceHolder.getMaster()) {
            return "master";
        }
        if (this.slaveDataSources.size() != 1) {
            synchronized (this) {
                this.slaveIndex++;
                if (this.slaveIndex >= this.slaveDataSources.size()) {
                    this.slaveIndex = 0;
                }
            }
        }
        return "slave-" + this.slaveIndex;
    }

    public void setMasterDataSource(DataSource masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    public void setSlaveDataSources(List<DataSource> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    @Autowired
    public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
        DynamicDataSourceHolder.transactionManager = dataSourceTransactionManager;
    }

}
