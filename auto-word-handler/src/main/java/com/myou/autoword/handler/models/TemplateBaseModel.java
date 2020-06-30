package com.myou.autoword.handler.models;

public interface TemplateBaseModel {
    // 指定模板名称（适用于oss等云上传时，适用该名称作为资源名称）
    String getFileName();
}
