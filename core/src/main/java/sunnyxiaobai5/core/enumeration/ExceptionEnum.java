package sunnyxiaobai5.core.enumeration;

public enum ExceptionEnum {
    EXPORT_NO_ANNOTATION(1, "没有任何列添加导出注解"),
    EXPORT_NO_COLUMN(10001, "没有要导出的列");

    private Integer key;

    private String message;

    ExceptionEnum(Integer key, String message) {
        this.key = key;
        this.message = message;
    }

    public Integer getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
