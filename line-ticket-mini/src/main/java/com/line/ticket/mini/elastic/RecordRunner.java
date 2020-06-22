package com.line.ticket.mini.elastic;

import org.apache.commons.io.LineIterator;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;

public class RecordRunner implements Runnable {

    private final String dayno;

    private final String path;

    public RecordRunner(String dayno, String path) {
        this.dayno = dayno;
        this.path = path;
    }


    @Override
    public void run() {
        try (FileReader reader = new FileReader(path)){
            int count = 0;
            LineIterator iterator = new LineIterator(reader);
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                if (StringUtils.isEmpty(line))
                    continue;
                String[] data = line.split("\t");
                if (data.length != 3 || data[0].isBlank() || data[1].isBlank() || data[2].isBlank())
                    continue;
                Record record = new Record();
                record.setStrategy(data[0]);
                record.setBuuid(data[1]);
                record.setMediaIds(data[2]);
                record.setDayno(dayno);
                count++;
            }
            System.out.println(Thread.currentThread().toString() + " handled " + path + ", consumed " + count + " records.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
