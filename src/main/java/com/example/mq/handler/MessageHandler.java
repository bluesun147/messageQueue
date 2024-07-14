package com.example.mq.handler;

import com.example.mq.model.Message;

public interface MessageHandler {
    void handle(final Message message);
}