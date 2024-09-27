package top.soft.bookonline.util;

public class AjaxResult {
    private int code; // 状态码
    private String message; // 提示信息
    private Object data; // 额外数据

    public AjaxResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(200, "成功", data);
    }

    public static AjaxResult error(String message) {
        return new AjaxResult(500, message, null);
    }

}
