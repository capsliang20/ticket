package com.line.ticket.log.hadoop.record;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StationPartitioner extends Partitioner<LongWritable, Text> {
    private RecordParser parser = new RecordParser();

    @Override
    public int getPartition(LongWritable longWritable, Text text, int numPartitions) {
        parser.parse(text);
        return getPartition(parser.getYear());
    }

    private int getPartition(String stationId) {
        return Integer.parseInt(stationId);
    }
}
