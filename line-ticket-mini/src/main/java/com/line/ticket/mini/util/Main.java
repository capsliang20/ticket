package com.line.ticket.mini.util;

import com.google.protobuf.util.JsonFormat;
import com.line.ticket.mini.model.Comment;
import com.line.ticket.mini.model.PbComment;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
//        Comment comment = new Comment();
//        comment.setCommentContent("comment_content");
//        comment.setReplyCount(10);
//        comment.setStatus(true);
//
//        PbComment.Comment.Builder builder = PbComment.Comment.newBuilder();
//        JsonFormat.parser().merge(JSON.toJSONString(comment), builder);
//        PbComment.Comment result = builder.build();
//
//        System.out.println(result);
//        System.out.println("-------");
//        System.out.println(result.toByteString());
//        System.out.println("-------");
//        System.out.println(Arrays.toString(result.toByteArray()));
//        System.out.println("-------");
//        System.out.println(new String(result.toByteArray()));
        String result = HttpTool.post("http://localhost:8080/mini/demo/proto",
                Map.of("commentContent", "commentContent"
                        , "replyCount", 10
                        , "status", true));
        System.out.println(result);
        System.out.println("-------");

        PbComment.Comment builder = PbComment.Comment.parseFrom(result.getBytes());
        String commentStr = JsonFormat.printer().print(builder);
        System.out.println(commentStr);
        System.out.println("-------");
        Comment comment = JSON.readValue(commentStr, Comment.class);
        System.out.println(JSON.toJSONString(comment));

    }
}
