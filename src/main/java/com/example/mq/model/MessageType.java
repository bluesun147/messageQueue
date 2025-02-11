package com.example.mq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    NOTI("알림");

    private final String desc;
}
