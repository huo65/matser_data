package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 * @author huozj
 * @TableName procurement_master
 */
@TableName(value ="procurement_master")
@Data
public class ProcurementMaster implements Serializable {
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
     * 零件号：零件唯一标识
     */
    @TableField(value = "part_id")
    private String partId;

    /**
     * 生产制成时间: 单位：分钟。此处配置的生产时用于产生Call-Off指令
     */
    @TableField(value = "production_completion_time")
    private Integer productionCompletionTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}