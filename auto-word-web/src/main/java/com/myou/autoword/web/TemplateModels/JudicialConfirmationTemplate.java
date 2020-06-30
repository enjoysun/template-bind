package com.myou.autoword.web.TemplateModels;

import com.myou.autoword.handler.elements.DateSlicer;
import com.myou.autoword.handler.models.TemplateBaseModel;
import lombok.Data;

/**
 * @Author myou
 * @Date 2020/6/30  3:19 下午
 */
@Data
public class JudicialConfirmationTemplate implements TemplateBaseModel {
    /**
     * 模板信息
     */
    private String fileName = "司法确认申请书";

    /**
     * 双发申请人信息
     */

    private String plaintiff;
    private String defendant;
    private String subject;
    private String mediateYear;
    private String mediateMonth;
    private String mediateDay;
    private String mediator;
    private String organization;

    /**
     * 签署日期
     */
    private DateSlicer dateSlicer;

    @Override
    public String getFileName() {
        return fileName;
    }
}
