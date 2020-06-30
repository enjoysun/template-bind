package com.myou.autoword.handler.services.impl;

import com.myou.autoword.handler.exceptions.TemplateDataBindException;
import com.myou.autoword.handler.exceptions.TemplateValidationException;
import com.myou.autoword.handler.models.TemplateBaseModel;
import com.myou.autoword.handler.results.FailResult;
import com.myou.autoword.handler.results.InvokeResult;
import com.myou.autoword.handler.services.ResultActionProxy;
import com.myou.autoword.handler.utils.TemplateBindValidation;
import com.myou.autoword.handler.utils.TemplateBinding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  11:30 上午
 */
public class TemplateBindAndUpload {
    private TemplateBinding templateBinding;
    private TemplateBindValidation templateBindValidation;
    private TemplateBaseModel model;

    public TemplateBindAndUpload() {
        templateBinding = new TemplateBinding();
        templateBindValidation = new TemplateBindValidation();
    }

    public TemplateBindAndUpload(TemplateBaseModel templateModel) {
        this();
        this.model = templateModel;
    }

    /**
     * @return boolean 绑定成功并上传oss结果
     * @throws IllegalAccessException       无法访问反射的templateModel异常
     * @throws UnsupportedEncodingException 编码不支持异常
     */
    public InvokeResult invokeBindAndProxyResult(ResultActionProxy proxy) throws IllegalAccessException {
        if (null == model) {
            throw new TemplateDataBindException("missing template bind data");
        }
        try {
            Map<String, Object> map = templateBindValidation.transferToMap(model);
            if (null != proxy) {
                return InvokeResult.builder().isSuccess(proxy.proxy(templateBinding.createWord(map), map)).resultStream(new byte[0]).failResult(null).build();
            }
            return InvokeResult.builder().isSuccess(true).resultStream(templateBinding.createWord(map)).failResult(null).build();
        } catch (IOException ex) {
            return InvokeResult.builder().isSuccess(false).resultStream(null).failResult(
                    FailResult.builder().failMsg("ImageData bytes get error").modelName(model.getFileName()).build()
            ).build();
        }
    }
}
