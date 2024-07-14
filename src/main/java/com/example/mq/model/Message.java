package com.example.mq.model;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Slf4j
public class Message<T> {
    private MessageType messgeType;
    private T content;
    private int failCount = 0;
    private int tolerance = 3;
    private List<Exception> exceptionList = new ArrayList<>();

    // content 타입 NOTI로 강제하기. 핸들러에서 문제 발생 가능

    public Message(MessageType messageType, T content) {
        this.messgeType = messageType;
        this.content = content;
    }

    public void addExeption(Exception e) {
        this.exceptionList.add(e);
    }

    public void increaseFailCount() {
        failCount += 1;
    }
}
