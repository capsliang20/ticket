package com.line.ticket.common.entity.generic;

public abstract class AbstractBodyRequest<T> extends AbstractRequest implements BodyRequest<T> {
    private static final long serialVersionUID = 6884952898169293948L;
    T body;

    @Override
    public T getBody() {
        return body;
    }

    @Override
    public void setBody(T body) {
        this.body = body;
    }
}
