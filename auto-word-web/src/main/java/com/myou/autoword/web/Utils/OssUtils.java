package com.myou.autoword.web.Utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author myou
 * @Date 2020/6/30  3:03 下午
 */
public class OssUtils {
    private OSSClient genOssClient() {
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAINARqI3JMgq";
        String accessKeySecret = "rjR4KmgbG4R0aPYMb73Caip2b76t4L";
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public boolean upload(String ossFileName, byte[] data) {
        OSSClient client = this.genOssClient();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
            client.putObject("dispute-api", ossFileName, bais);
            return true;
        } catch (IOException err) {
            return false;
        } finally {
            client.shutdown();
        }
    }
}
