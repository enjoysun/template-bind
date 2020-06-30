package com.myou.autoword.handler.utils;

import com.myou.autoword.handler.elements.CheckBox;
import com.myou.autoword.handler.elements.DateSlicer;
import com.myou.autoword.handler.elements.ImageData;
import com.myou.autoword.handler.exceptions.TemplateValidationException;
import com.myou.autoword.handler.models.TemplateBaseModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  11:11 上午
 */
public class TemplateBindValidation {
    /**
     * 数据验证并组装
     *
     * @param model 模板类
     * @return
     * @throws IllegalAccessException
     */
    public Map<String, Object> transferToMap(TemplateBaseModel model) throws IllegalAccessException, IOException {
        if (null == model)
            throw new RuntimeException("template model can not be null");
        Class<? extends TemplateBaseModel> modelClass = model.getClass();
        HashMap<String, Object> objectHashMap = new HashMap<>();
        Field[] fields = modelClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (null == field.get(model))
                throw new TemplateValidationException(String.format("%s empty not allowed", field.getName()));
            if (field.getType() == DateSlicer.class) {
                DateSlicer slicer = (DateSlicer) field.get(model);
                objectHashMap.put("year", Integer.toString(slicer.getYear()));
                objectHashMap.put("month", Integer.toString(slicer.getMonth()));
                objectHashMap.put("day", Integer.toString(slicer.getDay()));
                continue;
            }
            if (field.getType() == ImageData.class) {
                ImageData imageData = (ImageData) field.get(model);
                objectHashMap.put(field.getName(), imageData.getImageElement());
                continue;
            }
            if (field.getType() == CheckBox.class) {
                CheckBox checkBox = (CheckBox) field.get(model);
                objectHashMap.put(field.getName(), checkBox.getCheckBoxElement(field.getName()));
                continue;
            }
            objectHashMap.put(field.getName(), field.get(model));
        }
        objectHashMap.put("templateName", String.format("%s.ftl", modelClass.getSimpleName()));
        return objectHashMap;
    }
}
