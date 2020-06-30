package com.myou.autoword.handler.services;

import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  2:28 下午
 */
public interface ResultActionProxy {
    boolean proxy(byte[] file, Map<String, Object> map);
}
