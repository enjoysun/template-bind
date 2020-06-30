package com.myou.autoword.handler.elements;

import java.util.Map;

/**
 * @Author myou
 * @Date 2020/6/30  10:59 上午
 */
public class CheckBox {
    private int fontSize;
    private Map<String, String> map;

    public String getValue() {
        return value;
    }

    private String value;

    public CheckBox() {
    }

    public CheckBox(Map<String, String> map, String value) {
        this();
        this.fontSize = 28;
        this.map = map;
        this.value = value;
    }

    /**
     * @param fontSize 字体大小(egg: 18, 32与字号大小对应的数字对应)
     * @param map      Key：选中值  Value：多选文本
     */
    public CheckBox(int fontSize, Map<String, String> map, String value) {
        this(map, value);
        this.fontSize = fontSize;
    }

    public String getCheckBoxElement(String fileName) {
        String checkBoxHeader = "<w:p>\n" +
                "<w:r>\n" +
                "<w:rPr/>";
        String checkBoxTail = "</w:r>\n" +
                "</w:p>\n" +
                "<w:p/>";
        String blank = "<w:rPr><w:rFonts w:ascii=\"宋体\" w:h-ansi=\"宋体\" w:cs=\"宋体\" w:hint=\"default\"/></w:rPr>\n" +
                "            <w:t>         </w:t>";

        String isCheckBoxElement = "<w:sym w:font=\"Wingdings\" w:char=\"00FE\"/></w:r>\n" +
                "<w:r>\n" +
                "<w:rPr><w:rFonts w:ascii=\"宋体\" w:h-ansi=\"宋体\" w:cs=\"宋体\" w:hint=\"default\"/></w:rPr>";
        String isNotCheckBoxElement = "<w:sym w:font=\"Wingdings\" w:char=\"00A8\"/></w:r>\n" +
                "<w:r>\n" +
                "<w:rPr><w:rFonts w:ascii=\"宋体\" w:h-ansi=\"宋体\" w:cs=\"宋体\" w:hint=\"default\"/></w:rPr>";
        String checkBoxBody = "<w:t> </w:t></w:r>\n" +
                "<w:r>\n" +
                "<w:rPr><w:rFonts w:ascii=\"宋体\" w:h-ansi=\"宋体\" w:cs=\"宋体\" w:hint=\"default\"/></w:rPr>\n" +
                "<w:sz w:val=\"%d\"/><w:sz-cs w:val=\"%d\"/>\n" +
                "<w:t> %s</w:t>";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(checkBoxHeader);
        map.forEach((key, val) -> {
            if (key.equals(this.value)) {
                stringBuilder.append(isCheckBoxElement);
            } else {
                stringBuilder.append(isNotCheckBoxElement);
            }
            String s = String.format(checkBoxBody, this.fontSize, this.fontSize, val);
            stringBuilder.append(s);
            stringBuilder.append(blank);
        });
        stringBuilder.append(checkBoxTail);
        return stringBuilder.toString();
    }
}
