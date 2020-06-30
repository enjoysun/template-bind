package com.myou.autoword.handler.exceptions;

/**
 * @Author myou
 * @Date 2020/6/30  10:58 上午
 * model字段类型验证异常
 */
public class TemplateValidationException extends RuntimeException {
    public TemplateValidationException(String message) {
        super(message);
    }
}
