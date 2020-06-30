package com.myou.autoword.handler.elements;

import com.myou.autoword.handler.exceptions.TemplateValidationException;
import com.myou.autoword.handler.utils.TemplateBinding;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author myou
 * @Date 2020/6/30  11:00 上午
 */
public class ImageData {
    private String ossFileName;
    private int width;
    private int height;
    private String imageAttrs;
    private String fileNameNoExtension;
    private String fileExtension;
    private String imageTitle;
    private String fileName;
    private String base64;


    public ImageData(String base64) {
        this.base64 = base64;
    }

    public ImageData(String base64, String ossFileName) {
        this(base64);
        if ((StringUtils.isNotEmpty(ossFileName) && ossFileName.lastIndexOf(".") > 0)) {
            this.ossFileName = ossFileName;
            this.fileNameNoExtension = FilenameUtils.getBaseName(ossFileName);
            this.fileExtension = FilenameUtils.getExtension(ossFileName);
            this.fileName = FilenameUtils.getName(ossFileName);
        }
    }

    /**
     * init
     *
     * @param width       image宽度设置
     * @param height      image高度设置
     * @param ossFileName ossFileName 如(test/test/110110.png)
     * @param imageTitle  image标题(选填)
     * @param base64 图片base64
     */
    public ImageData(int width, int height, String imageTitle, String base64, String ossFileName) {
        this(base64, ossFileName);
        this.imageTitle = StringUtils.isNotEmpty(imageTitle) ? imageTitle : "";
        this.width = width;
        this.height = height;
        this.imageAttrs = "<w:pict><w:binData w:name=\"wordml://%s\" xml:space=\"preserve\">\n" +
                "\n" +
                "%s\n" +
                "\n" +
                "\n" +
                "</w:binData><v:shape id=\"%s\" o:spid=\"_x0000_i1025\" type=\"#_x0000_t75\" style=\"width:%dpt;height:%dpt;visibility:visible;mso-wrap-style:square\"><v:imagedata src=\"wordml://%s\" o:title=\"%s\"/></v:shape></w:pict>";
    }

    public String getImageElement() throws IOException {
        if (!StringUtils.isNotEmpty(base64)) {
            throw new TemplateValidationException("imageBase64 can not be null");
        }
        String imageStr = base64;
        if (StringUtils.isNotEmpty(this.imageAttrs)) {
            imageStr = String.format(
                    this.imageAttrs,
                    this.fileName,
                    imageStr,
                    this.fileNameNoExtension,
                    this.width,
                    this.height,
                    this.fileName,
                    this.imageTitle);
        }
        return imageStr;
    }

    /**
     * 图片的Base64编码
     *
     * @param ossFileName oss文件标准输入流
     * @return
     */
//    public String getImageStr(String ossFileName) throws IOException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ossUtils.download(ossFileName, outputStream);
//        byte[] data = outputStream.toByteArray();
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);
//    }
}
