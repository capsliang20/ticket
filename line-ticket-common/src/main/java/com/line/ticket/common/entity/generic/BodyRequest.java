package com.line.ticket.common.entity.generic;

public interface BodyRequest<T> extends Request {

    T getBody();

    void setBody(T body);
}
