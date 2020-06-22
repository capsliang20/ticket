package com.line.ticket.mini.elastic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler {

    private static final String DIR_PREFEX = "data";

    private static final String LOCAL_TMP_DIR = "D://test/tmp/";

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9001");
        FileSystem fs = FileSystem.get(conf);
        Path root = new Path("/center");

        int dayno = 0;
        for (FileStatus fileStatus : fs.listStatus(root)) {
            int tmp = Integer.parseInt(fileStatus.getPath().getName().substring(DIR_PREFEX.length()));
            if (tmp > dayno)
                dayno = tmp;
        }
        List<Path> pathList = new LinkedList<>();
        for (FileStatus fileStatus : fs.listStatus(new Path(root, DIR_PREFEX + dayno))) {
            System.out.println(fileStatus);
            if (Math.abs(fileStatus.getPath().getName().hashCode()) % 4 == 1)
                pathList.add(fileStatus.getPath());
        }

        for (Path path : pathList) {
            Path localPath = new Path(LOCAL_TMP_DIR + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "/", path.getName());
            fs.copyToLocalFile(path, localPath);
        }
    }

    public static void handle(int shardingItem, int totalCount) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:90010");
        FileSystem fs = FileSystem.get(conf);
        Path root = new Path("/center");

        int dayno = 0;
        for (FileStatus fileStatus : fs.listStatus(root)) {
            int tmp = Integer.parseInt(fileStatus.getPath().getName().substring(DIR_PREFEX.length()));
            if (tmp > dayno)
                dayno = tmp;
        }
        List<Path> pathList = new LinkedList<>();
        for (FileStatus fileStatus : fs.listStatus(new Path(root, DIR_PREFEX + dayno))) {
            System.out.println(fileStatus);
            if (Math.abs(fileStatus.getPath().getName().hashCode()) % totalCount == shardingItem)
                pathList.add(fileStatus.getPath());
        }


        ExecutorService executors = Executors.newFixedThreadPool(8);

        for (Path path : pathList) {
            Path localPath = new Path(LOCAL_TMP_DIR + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + "/", path.getName());
            fs.copyToLocalFile(path, localPath);
            executors.execute(new RecordRunner(String.valueOf(dayno), localPath.toString()));
        }
    }
}
