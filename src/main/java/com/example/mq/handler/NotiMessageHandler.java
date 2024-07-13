package com.example.mq.handler;


import com.example.mq.NotiSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

@Slf4j
Component
@RequiredArgsConstructor
public class NotiMessageHandler implements MessageHandler {
    private final NotiSender notiSender;

    public NotiMessageHandler(NotiSender notiSender) {
        this.notiSender = notiSender;
    }

    @Override
    public void handle(final Message message) {
        if (message.getMessageType() != MessageType.NOTI) {
            return;
        }
        notiSender.send(message.getContent());

        log.info("메시지 NotiMessageHandelr에 의해 처리");
    }
}
