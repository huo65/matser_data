package cn.xidian.master_data.model.dto.process;

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
public class ProcessMasterQueryRequest extends PageRequest implements Serializable {
    /**
     * 零件号：零件唯一标识
     */
    private String partId;


    /**
     * 消耗工位: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    private String consumptionPosition;



    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}