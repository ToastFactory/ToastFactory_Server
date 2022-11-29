package com.konkuk.toastfactory.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends Throwable {
    private final BaseResponseStatus status;
}
