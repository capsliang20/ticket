package com.line.ticket.mini.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@Configuration
public class CustomShardingDatabase {
//    @Bean
    public DataSource customShardingDatabase1() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://39.108.225.165:3306/ds0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://39.108.225.165:3306/ds1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds1", dataSource2);

        TableRuleConfiguration ruleConfiguration = new TableRuleConfiguration("t_info");
        ruleConfiguration.setKeyGeneratorConfig(null);
        PreciseShardingAlgorithm<String> dbAlgorithm = new PreciseShardingAlgorithm() {
            @Override
            public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
                return null;
            }
        };
        StandardShardingStrategyConfiguration dbShardingStrategyConfig = new StandardShardingStrategyConfiguration("id", dbAlgorithm);
        ruleConfiguration.setDatabaseShardingStrategyConfig(dbShardingStrategyConfig);

        PreciseShardingAlgorithm tableShardingAlgorithm = new PreciseShardingAlgorithm() {
            @Override
            public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
                return null;
            }
        };
        StandardShardingStrategyConfiguration tableShardingStrategyConfig = new StandardShardingStrategyConfiguration("id", tableShardingAlgorithm);
        ruleConfiguration.setTableShardingStrategyConfig(tableShardingStrategyConfig);

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(ruleConfiguration);

        DataSource shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        return shardingDataSource;
    }
}
