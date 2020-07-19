package com.line.ticket.log.netty.other;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOAcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AIOTimeServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel result, AIOTimeServerHandler attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new AIOReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AIOTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
