package com.example.mq.handler;


import com.example.mq.NotiSender;
import com.example.mq.model.MessageType;
import com.example.mq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotiMessageHandler implements MessageHandler {

    private final NotiSender notiSender;
    private final MessageFailHandler failHandler;


    @Override
    public void handle(final Message message) {
        if (message.getMessgeType() != MessageType.NOTI) {
            return;
        }
        notiSender.send((String) message.getContent());

        log.info("메시지 NotiMessageHandler에 의해 처리");
    }
}
