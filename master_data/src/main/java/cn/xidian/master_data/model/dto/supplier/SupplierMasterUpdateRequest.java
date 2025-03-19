package cn.xidian.master_data.model.dto.supplier;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 */
@TableName(value ="supplier_master")
@Data
public class SupplierMasterUpdateRequest implements Serializable {
    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商地址
     */
    private String supplierAddress;

    /**
     * 运输时间: 单位：分钟。此处配置的运输时间非精确时间，用于产生Call-Off指令，不用于产生运输计划
     */
    private Integer deliveryTime;

    /**
     * 入厂运输模式
     */
    private String inFactoryDeliveryMode;

    /**
     * 送货间隔时间: 单位：分钟
     */
    private Integer deliveryIntervalTime;

}