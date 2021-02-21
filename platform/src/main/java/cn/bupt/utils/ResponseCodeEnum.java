package cn.bupt.utils;

public enum ResponseCodeEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败");

    Integer code;
    String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
