package com.line.ticket.mini.mapper.shard;

import com.line.ticket.mini.model.shard.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShardRecordMapper {
    List<Record> queryRecord(@Param("id") Long id, @Param("dateNum") Integer dateNum);

    Integer coverRecord(Record record);

    Integer removeRecord(@Param("id") Long id, @Param("dateNum") Integer dateNum);
}
