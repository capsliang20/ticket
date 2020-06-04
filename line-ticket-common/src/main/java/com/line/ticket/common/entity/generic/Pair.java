package com.line.ticket.common.entity.generic;

import java.io.Serializable;
import java.util.Map;

public final class Pair<K, V> implements Map.Entry<K, V>, Serializable {
    private static final long serialVersionUID = 4082235622288235630L;
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = value;
        return oldValue;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
