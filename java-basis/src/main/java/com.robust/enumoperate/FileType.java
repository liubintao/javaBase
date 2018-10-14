package com.robust.enumoperate;

/**
 * @author bintao
 * @Despription
 * @date 2018/4/16 14:24
 */
public enum FileType {
    TXT("01","123");
    private String code;
    private String desc;
    /**
     * 文件扩展名
     */
    private String ext;
    /**
     * 文件分隔符
     */
    private String separator;

    FileType(String code, String desc, String ext, String separator) {
        this.code = code;
        this.desc = desc;
        this.ext = ext;
        this.separator = separator;
    }

    FileType(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getExt() {
        return ext;
    }

    public String getSeparator() {
        return separator;
    }
}
