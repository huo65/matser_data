package cn.xidian.master_data.common;

import lombok.Getter;

/**
 * 错误码
 */
@Getter
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS(200, "ok"),
    /**
     * 请求参数错误
     */
    PARAMS_ERROR(40000, "请求参数错误"),
    /**
     * 重复插入
     */
     REPEAT_ERROR(40010, "数据已存在"),
    /**
     * 请求数据不存在
     */
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    /**
     * 禁止访问
     */
    FORBIDDEN_ERROR(40300, "禁止访问"),
    /**
     * 系统内部异常
     */
    SYSTEM_ERROR(50000, "系统内部异常"),
    /**
     * 操作错误
     */
    OPERATION_ERROR(50010, "操作失败"),
    /**
     * 零件号错误
     */
    PART_ID_ERROR(50020,"零件号不存在"),
    /**
     * 零件已配置
     */
    PART_CONFIGURED(50030,"零件已配置"),
    /**
     * 供应商已配置
     */
    SUPPLIER_CONFIGURED(50040,"供应商已配置"),
    /**
     * 供应商代码错误
     */
    SUPPLIER_CODE_ERROR(50050,"供应商代码不存在");



    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


}