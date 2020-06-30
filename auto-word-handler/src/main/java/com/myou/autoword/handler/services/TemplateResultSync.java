package com.myou.autoword.handler.services;

import com.myou.autoword.handler.results.InvokeResult;

import java.util.List;

/**
 * @Author myou
 * @Date 2020/6/30  11:29 上午
 */
public interface TemplateResultSync {
    void invokeSyncFailed(boolean isSuccess, String failMsg);

    void invokeSync(boolean isSuccess, List<InvokeResult> msg);
}
