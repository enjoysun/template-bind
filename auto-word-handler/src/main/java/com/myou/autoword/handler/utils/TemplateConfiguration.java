package com.myou.autoword.handler.utils;

import freemarker.template.Configuration;

/**
 * @Author myou
 * @Date 2020/6/30  11:10 上午
 */
public class TemplateConfiguration {
    private static class TemplateConfigurationHolder {
        public static Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    }

    public static Configuration getConfiguration() {
        return TemplateConfigurationHolder.configuration;
    }
}
