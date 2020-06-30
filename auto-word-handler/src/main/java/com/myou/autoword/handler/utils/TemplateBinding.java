package com.myou.autoword.handler.utils;

import com.myou.autoword.handler.exceptions.TemplateDataBindException;
import freemarker.template.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  11:13 上午
 */
public class TemplateBinding {
    private Logger log = LoggerFactory.getLogger(TemplateBinding.class.toString());
    private Configuration config;

    public TemplateBinding() {
        config = TemplateConfiguration.getConfiguration();
        config.setDefaultEncoding("utf-8");
    }

    /**
     * FreeMarker生成Word
     *
     * @param dataMap 数据
     */
    public byte[] createWord(Map<String, Object> dataMap) throws UnsupportedEncodingException {
        config.setClassForTemplateLoading(this.getClass(), "/templates");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        if (!dataMap.containsKey("templateName")) {
            throw new TemplateDataBindException(String.format("%s templateName can not be null", dataMap.getClass()));
        }
        if (!dataMap.containsKey("fileName")) {
            throw new TemplateDataBindException(String.format("%s fileName can not be null", dataMap.getClass()));
        }
        String templateName = dataMap.get("templateName").toString();
        Template template = null;
        if (templateName.endsWith(".ftl")) {
            templateName = templateName.substring(0, templateName.indexOf(".ftl"));
        }
        try {
            template = config.getTemplate(templateName + ".ftl");
        } catch (TemplateNotFoundException e) {
            log.info("模板文件未找到");
            throw new TemplateDataBindException("模板文件未找到");
        } catch (MalformedTemplateNameException e) {
            log.info("模板类型不正确");
            throw new TemplateDataBindException("模板类型不正确");
        } catch (IOException e) {
            log.info("IO读取失败");
            throw new TemplateDataBindException("IO读取失败,检查ftl模板占位符是否完整");
        }
        // 采用StringWriter内存char[]缓存流进行freemarker渲染结果保存
        StringWriter stringWriter = new StringWriter();

        //将模板中的预先的代码替换为数据
        try {
            template.process(dataMap, stringWriter);
        } catch (TemplateException e) {
            log.info("填充模板时异常");
            throw new TemplateDataBindException("填充模板时异常");
        } catch (IOException e) {
            log.info("IO读取时异常");
            throw new TemplateDataBindException("IO读取时异常");
        }
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }
}
