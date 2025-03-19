package cn.xidian.master_data.model.dto.procurement;

import cn.xidian.master_data.common.PageRequest;
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
public class ProcurementMasterQueryRequest extends PageRequest implements Serializable {
    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 零件号：零件唯一标识
     */
    private String partId;



    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}