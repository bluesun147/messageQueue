package com.example.mq.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

@Slf4j
Component
@RequiredArgsConstructor
public class NotiMessageHandler implements MessageHandler {
    private final NotiSender notiSender;

    @Override
    public void handle(final Message message) {
        notiSender.send();
    }
}
