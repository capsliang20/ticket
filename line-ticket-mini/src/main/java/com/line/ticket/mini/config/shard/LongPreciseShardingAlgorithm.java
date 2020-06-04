package com.line.ticket.mini.config.shard;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 主要用于对雪花算法生成的id进行分片
 */
public class LongPreciseShardingAlgorithm extends AbstractPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        return getTargetName(availableTargetNames, shardingValue.getValue().toString().hashCode());
    }
}
