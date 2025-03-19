package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 * @TableName procurement_master
 */
@TableName(value ="procurement_master")
@Data
public class ProcurementMaster implements Serializable {
    /**
     * 供应商代码
     */
    @MppMultiId
    @TableField(value = "supplier_code")
    private String supplierCode;

    /**
     * 零件号：零件唯一标识
     */
    @MppMultiId
    @TableField(value = "part_id")
    private String partId;

    /**
     * 生产制成时间: 单位：分钟。此处配置的生产时用于产生Call-Off指令
     */
    @TableField(value = "production_completion_time")
    private Integer productionCompletionTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}