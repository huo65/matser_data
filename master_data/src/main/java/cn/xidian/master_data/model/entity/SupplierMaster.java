package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 * @author huozj
 * @TableName supplier_master
 */
@TableName(value ="supplier_master")
@Data
public class SupplierMaster implements Serializable {
    /**
     * 非业务主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 供应商代码
     */
    @TableField(value = "supplier_code")
    private String supplierCode;

    /**
     * 供应商名称
     */
    @TableField(value = "supplier_name")
    private String supplierName;

    /**
     * 供应商地址
     */
    @TableField(value = "supplier_address")
    private String supplierAddress;

    /**
     * 运输时间: 单位：分钟。此处配置的运输时间非精确时间，用于产生Call-Off指令，不用于产生运输计划
     */
    @TableField(value = "delivery_time")
    private Integer deliveryTime;

    /**
     * 入厂运输模式
     */
    @TableField(value = "in_factory_delivery_mode")
    private String inFactoryDeliveryMode;

    /**
     * 送货间隔时间: 单位：分钟
     */
    @TableField(value = "delivery_interval_time")
    private Integer deliveryIntervalTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}