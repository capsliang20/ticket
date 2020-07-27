package com.line.ticket.common.entity.generic;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Pair<K, V> implements Serializable {
    private static final long serialVersionUID = 4082235622288235630L;

    private K key;

    private V value;
}
