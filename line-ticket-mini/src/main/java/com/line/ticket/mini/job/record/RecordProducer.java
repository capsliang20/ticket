package com.line.ticket.mini.job.record;

import com.line.ticket.mini.model.shard.Record;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class RecordProducer implements Callable<Integer> {
    private final ArrayBlockingQueue<Record> recordQueue;

    private final Path[] paths;

    private final Signal signal = new Signal();

    private static final char ABANDON_DELIMITER = '\r';

    private static final char ROW_DELIMITER = '\n';

    private static final String FIELD_DELIMITER = ",";

    RecordProducer(Path[] paths, ArrayBlockingQueue<Record> recordQueue) {
        this.recordQueue = recordQueue;
        this.paths = paths;
    }

    public Signal getSignal() {
        return signal;
    }

    @Override
    public Integer call() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        signal.mark = true;
        int count = 0;
        for (Path path : paths) {
            System.out.println("producer: " + Thread.currentThread().toString() + ". Now handle path: " + path);
            try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
                StringBuilder stringBuilder = new StringBuilder();
                char current;
                while (fileChannel.read(byteBuffer) > 0) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        current = (char) byteBuffer.get();
                        if (current != ABANDON_DELIMITER && current != ROW_DELIMITER) {
                            stringBuilder.append(current);
                            continue;
                        }
                        if (current == ROW_DELIMITER) {
                            byteBuffer.mark();
                            String[] fields = stringBuilder.toString().split(FIELD_DELIMITER);
                            if (fields.length == 4) {
                                Record record = new Record();
                                record.setType(1);
                                record.setId(Long.parseLong(fields[1]));
                                record.setContent(fields[2]);
                                record.setDateNum(Integer.parseInt(fields[3]));
                                while (!recordQueue.offer(record, 1, TimeUnit.SECONDS)) {
                                    System.out.println("producer: " + Thread.currentThread().toString() + "now queue is full");
                                }
                                count++;
                                System.out.println("producer: " + Thread.currentThread().toString() + "now queue's size is " + recordQueue.size());
                            }
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    }
                    byteBuffer.reset();
                    byteBuffer.compact();
                    stringBuilder.delete(0, stringBuilder.length());
                }
            } catch (IOException e) {
                signal.mark = false;
                System.out.println("producer: " + Thread.currentThread().toString() + " open file error.");
                e.printStackTrace();
            } catch (InterruptedException e) {
                signal.mark = false;
                System.out.println("producer: " + Thread.currentThread().toString() + " interruptedException.");
                e.printStackTrace();
            }
        }
        System.out.println("producer: " + Thread.currentThread().toString() + " finished handle " + count + " records.");
        signal.mark = false;
        return count;
    }

    static class Signal {
        volatile boolean mark = true;

        public Signal() {
        }
    }
}
