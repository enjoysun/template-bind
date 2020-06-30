package com.myou.autoword.handler.services.impl;

import com.myou.autoword.handler.models.TemplateBaseModel;
import com.myou.autoword.handler.results.FailResult;
import com.myou.autoword.handler.results.InvokeResult;
import com.myou.autoword.handler.services.ResultActionProxy;
import com.myou.autoword.handler.services.TemplateResultSync;
import com.myou.autoword.handler.utils.TemplateBindValidation;
import com.myou.autoword.handler.utils.TemplateBinding;
import com.myou.autoword.handler.utils.TemplateThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @Author myou
 * @Date 2020/6/30  2:08 下午
 */
public class TemplateGenerateSync {
    private static final Logger log = LoggerFactory.getLogger(TemplateGenerateSync.class.toString());
    private static final TemplateBinding templateBinding;
    private static final TemplateBindValidation templateBindValidation;
    private static final ExecutorService executorService = TemplateThreadPool.getExecutorService();

    static {
        templateBinding = new TemplateBinding();
        templateBindValidation = new TemplateBindValidation();
    }

    @SafeVarargs
    public static <T extends TemplateBaseModel> void invokeBindAndReturnResultSync(TemplateResultSync templateResultSync, ResultActionProxy proxy, T... templateModel) {
        CompletableFuture<List<InvokeResult>> completableFuture = CompletableFuture.supplyAsync(() -> {
            List<InvokeResult> resultList = new ArrayList<>();
            for (TemplateBaseModel model : templateModel) {
                InvokeResult result = new InvokeResult();
                try {
                    Map<String, Object> map = templateBindValidation.transferToMap(model);
                    byte[] bytes = templateBinding.createWord(map);
                    result.setSuccess(null == proxy || proxy.proxy(bytes, map));
                    result.setResultStream(null == proxy ? bytes : null);
                } catch (IOException ex) {
                    result.setSuccess(false);
                    FailResult failResult = new FailResult();
                    failResult.setModelName(model.getClass().getName());
                    failResult.setFailMsg("ImageData bytes get error");
                    result.setFailResult(failResult);
                } catch (Exception ex) {
                    result.setSuccess(false);
                    FailResult failResult = new FailResult();
                    failResult.setModelName(model.getClass().getName());
                    failResult.setFailMsg(ex.getMessage());
                    result.setFailResult(failResult);
                }
                resultList.add(result);
            }
            return resultList;
        }, executorService);
        // 执行成功
        completableFuture.thenAccept(result -> {
            log.info("批量template任务上传成功");
            templateResultSync.invokeSync(true, result);
        });
        // 执行异常
        completableFuture.exceptionally(e -> {
            log.error(String.format("template批量任务失败:%s", e.getMessage()));
            templateResultSync.invokeSyncFailed(false, e.getMessage());
            return null;
        });
    }
}
