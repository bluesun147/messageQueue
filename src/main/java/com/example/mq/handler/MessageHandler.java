package com.example.mq.handler;

import org.apache.logging.log4j.message.Message;

public interface MessageHandler {
    void handle(final Message message);
}