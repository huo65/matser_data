package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

/**
 * JIS基座开发完成后，工艺主数据以JIS基座中配置的为准
 * @author huozj
 * @TableName process_master
 */
@TableName(value ="process_master")
@Data
public class ProcessMaster implements Serializable {
    /**
     * 零件号：零件唯一标识
     */
    @MppMultiId
    @TableField(value = "part_id")
    private String partId;

    /**
     * 车型: 每个车型的零件消耗不一样，如果不配置则认为所有车型消耗量相同。也可以以通配符形式简化配置
     */
    @MppMultiId
    @TableField(value = "vehicle_model")
    private String vehicleModel;

    /**
     * 消耗工位: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    @MppMultiId
    @TableField(value = "consumption_position")
    private String consumptionPosition;

    /**
     * 工厂: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    @TableField(value = "factory")
    private String factory;

    /**
     * 车间: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    @TableField(value = "workshop")
    private String workshop;

    /**
     * 线体: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上
     */
    @TableField(value = "line")
    private String line;

    /**
     * 消耗用量: 单工位零件消耗量
     */
    @TableField(value = "consumption_amount")
    private Integer consumptionAmount;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}