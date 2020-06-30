package com.myou.autoword.web.ResultAutoProxys;

import com.myou.autoword.handler.services.ResultActionProxy;
import com.myou.autoword.web.Utils.OssUtils;

import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  2:58 下午
 */
public class OssProxy implements ResultActionProxy {
    @Override
    public boolean proxy(byte[] file, Map<String, Object> map) {
        OssUtils ossUtils = new OssUtils();
        Object name = map.get("fileName");
        return ossUtils.upload(String.format("test/test/%s.doc", name), file);
    }
}
