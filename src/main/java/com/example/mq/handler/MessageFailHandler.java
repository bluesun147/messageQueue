package com.example.mq.handler;

import com.example.mq.MessageQ;
import com.example.mq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageFailHandler {

    private final MessageQ messageQ;

    // 메시지 보내기 실패 경우
    public void handleFail(Message message, Exception e) {
        message.addExeption(e);
        message.increaseFailCount();
        // 정해진 실패 횟수 넘은 경우
        if (message.getFailCount() >= message.getTolerance()) {
            handleTooManyFails(message);
        } else {
            messageQ.add(message);
        }
    }

    private void handleTooManyFails(Message message) {
        log.info("최대 실패 횟수 " + message.getTolerance() + "회 초과.");

        log.info("예외 리스트 ------------------------");

        List<Exception> exceptionList = message.getExceptionList();
        log.info("실패한 메시지 : {}", message);

        for (int i=0; i<message.getExceptionList().size(); i++) {
            Exception e = exceptionList.get(i);
            e.printStackTrace();
            log.info("------------------------");
        }
        log.info("예외 리스트 끝 ------------------------");

    }
}