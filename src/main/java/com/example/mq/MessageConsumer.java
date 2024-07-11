package com.example.mq;

import jakarta.websocket.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@Component
public class MessageConsumer {
    private final List<MessageHandler> messageHandlerList;
    private final MessageQ messageQ;
    private final int threadPoolSize = 5;
    private boolean isTerminated = true;

    public MessageConsumer(List<MessageHandler> messageHandlerList, MessageQ messageQ) {
        this.messageHandlerList = messageHandlerList;
        this.messageQ = messageQ;
    }

    @Scheduled(cron = "0/1 * * * * *")
    public void consume() {
        log.info("메시지큐에 메시지 있는지 확인");
        if (!messageQ.isEmpty() && isTerminated) {
            executeThreadPool();
        }
    }

    private void executeThreadPool() {
        isTerminated = false;
        log.info(messageQ.size() + "개의 메시지 존재");
        log.info(threadPoolSize + "개의 스레드풀 생성해 메시지 처리");
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        // java8 반복, 0부터 threadPoolSize까지 방출
        IntStream.range(0, threadPoolSize).forEach(
                // CompletableFuture : 자바 비동기 인터페이스
                // runAsync : 반환값 없는 경우 비동기 작업 실행
                threadNum -> CompletableFuture.runAsync(this::process, executor));
        executor.shutdown();
    }

    private void process() {
        log.info("메시지 처리 준비중");
        Message message = null;
        // 메시지가 null 아닐 때
        while ((message = messageQ.poll()) != null) {
            log.info("메시지 처리");
            handleMessage(message);
        }
        isTerminated = true;
    }

    private void handleMessage(final Message message) {
        messageHandlerList.forEach(messageHandler -> {
            try {
                // messageHandler.handle(message);
            } catch (Exception e) {
                // failHandler.handleFail(message, e);
            }
        });
    }
}
