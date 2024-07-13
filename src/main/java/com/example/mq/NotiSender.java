package com.example.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotiSender {
    public void send(String notification) {
        log.info("알림 전송 " + notification);
        // 알림 전송 로직
    }
}
