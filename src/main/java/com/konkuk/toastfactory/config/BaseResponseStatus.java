package com.konkuk.toastfactory.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    DUPLICATE_NAME(false,2001,"이미 존재하는 이름입니다."),
    INVALID_MEMBER(false,2002,"존재하지 않는 유저입니다."),
    NOT_YET_START(false,2003,"게임을 한번도 시작하지 않은 유저입니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
