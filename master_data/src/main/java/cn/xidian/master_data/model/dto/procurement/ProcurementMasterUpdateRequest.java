package cn.xidian.master_data.model.dto.procurement;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 * @author huozj
 * @TableName procurement_master
 */
@TableName(value ="procurement_master")
@Data
public class ProcurementMasterUpdateRequest implements Serializable {
    private Integer id;
    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 零件号：零件唯一标识
     */
    private String partId;

    /**
     * 生产制成时间: 单位：分钟。此处配置的生产时用于产生Call-Off指令
     */
    private Integer productionCompletionTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}