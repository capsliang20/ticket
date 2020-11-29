package com.line.ticket.mini.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtoBufTool {
    public static byte[] toByteArray(Object obj, Message message) {
        Message.Builder builder = message.toBuilder();
        try {
            JsonFormat.parser().merge(JSON.toJSONString(obj), builder);
            return builder.build().toByteArray();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static <T> T toPojo(byte[] content, Message message, Class<T> pojoType) {

    }
}
