package cn.xidian.master_data.common;

/**
 * @author huozj
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 数据
     * @return {@link BaseResponse}<{@link T}>
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(2000, data, "ok");
    }
    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<>(2000, null, message);
    }
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(2000, data, message);
    }

    /**
     * 错误
     *
     * @param errorCode 错误代码
     * @param message   消息
     * @return {@link BaseResponse}<{@link T}>
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
    /**
     * 错误
     * 失败
     *
     * @param code    密码
     * @param message 消息
     * @return {@link BaseResponse}<{@link T}>
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }


}