package com.example.mq;

import com.example.mq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// 메시지 담길 메시지큐
@Component
@ToString
@RequiredArgsConstructor
public class MessageQ {
    // 동시성 처리 해결
    private final Queue<Message> queue = new ConcurrentLinkedQueue<>();

    // 메시지 추가
    public void add(Message noti) {
        queue.add(noti);
    }

    public Message poll() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
