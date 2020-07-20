package com.line.ticket.log.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.SnappyCodec;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Main {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        conf.setBoolean("io.output.compress", true);
        conf.set("io.compression.codecs", "org.apache.hadoop.io.compress.DefaultCodec,org.apache.hadoop.io.compress.GzipCodec,org.apache.hadoop.io.compress.BZip2Codec,org.apache.hadoop.io.compress.DeflateCodec,org.apache.hadoop.io.compress.SnappyCodec,org.apache.hadoop.io.compress.Lz4Codec,com.hadoop.compression.lzo.LzoCodec,com.hadoop.compression.lzo.LzopCodec");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/center/part-00031-365c1f38-edee-4ccf-bc98-09e49e762f12-c000.snappy");
        SnappyCodec codec = new SnappyCodec();
        codec.setConf(conf);
        CompressionInputStream in = codec.createInputStream(fs.open(path), codec.createDecompressor());
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        ReadableByteChannel inputChannel = Channels.newChannel(in);
        WritableByteChannel outChannel = Channels.newChannel(new FileOutputStream("D://test/tmp/out.txt"));
        while (inputChannel.read(buffer) > 0) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        System.out.println(fs.getFileStatus(path));
    }
}
