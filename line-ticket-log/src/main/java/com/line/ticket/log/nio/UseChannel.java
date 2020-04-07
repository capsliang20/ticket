package com.line.ticket.log.nio;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UseChannel {
    public static void main(String[] args) {
        nioCopyFile();
    }

    private static void nioCopyFile() {
        File srcFile = new File(ResourceConstant.SOURCE_PATH);
        File destFile = new File(ResourceConstant.DEST_PATH);
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;
            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();
                int length = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                while ((length = inChannel.read(buf)) != -1) {
                    buf.flip();
                    int outLength = 0;
                    while ((outLength = outChannel.write(buf)) != 0) {
                        System.out.println("写入字节数: " + outLength);
                    }
                    buf.clear();
                }
                outChannel.force(true);
            } finally {
                IOUtils.closeQuietly(outChannel);
                IOUtils.closeQuietly(fos);
                IOUtils.closeQuietly(inChannel);
                IOUtils.closeQuietly(fis);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("base 复制毫秒数: " + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
