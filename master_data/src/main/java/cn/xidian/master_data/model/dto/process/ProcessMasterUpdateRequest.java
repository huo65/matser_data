package cn.xidian.master_data.model.dto.process;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * JIS基座开发完成后，工艺主数据以JIS基座中配置的为准
 * @author huozj
 * @TableName process_master
 */
@Data
public class ProcessMasterUpdateRequest implements Serializable {
    /**
     * 零件号：零件唯一标识
     */
    private String partId;

    /**
     * 车型: 每个车型的零件消耗不一样，如果不配置则认为所有车型消耗量相同。也可以以通配符形式简化配置
     */
    private String vehicleModel;

    /**
     * 消耗工位: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    private String consumptionPosition;

    /**
     * 工厂: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    private String factory;

    /**
     * 车间: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    private String workshop;

    /**
     * 线体: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    private String line;

    /**
     * 消耗用量: 单工位零件消耗量
     */
    private Integer consumptionAmount;

    @Serial
    private static final long serialVersionUID = 1L;
}