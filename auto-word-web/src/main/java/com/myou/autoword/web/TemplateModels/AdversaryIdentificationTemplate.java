package com.myou.autoword.web.TemplateModels;

import com.myou.autoword.handler.elements.ImageData;
import com.myou.autoword.handler.models.TemplateBaseModel;
import lombok.Data;

/**
 * @Author myou
 * @Date 2020/6/30  2:56 下午
 */
@Data
public class AdversaryIdentificationTemplate implements TemplateBaseModel {
    /**
     * 模板名称
     */
    private String fileName = "当事人身份证明";

    /**
     * 主体信息
     */

    private String name;

    private String idCard;

    private ImageData obverseImg;

    private ImageData reverseImg;

    @Override
    public String getFileName() {
        return fileName;
    }
}
