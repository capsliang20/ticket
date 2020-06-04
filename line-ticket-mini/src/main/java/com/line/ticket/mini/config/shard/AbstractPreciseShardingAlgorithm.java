package com.line.ticket.mini.config.shard;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractPreciseShardingAlgorithm {
    /**
     * 要求备选集合的大小为2的幂
     */
    protected String getTargetName(Collection<String> availableTargetNames, int shardingValue) {
        int size;
        if (availableTargetNames == null || (size = availableTargetNames.size()) == 0 || (size & (size - 1)) != 0)
            throw new UnsupportedOperationException("availableTargetNames should not be null and its size should be power of 2.");

        int index = shardingValue & (size - 1);
        Iterator<String> iterator = availableTargetNames.iterator();
        String result;
        do {
            result = iterator.next();
        } while (--index >= 0 && iterator.hasNext());
        return result;
    }
}