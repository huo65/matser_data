package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 
 * @author huozj
 * @TableName logistics_master
 */
@TableName(value ="logistics_master")
@Data
public class LogisticsMaster implements Serializable {
    /**
     * 零件号: 零件唯一标识
     */
    @TableId(value = "part_id")
    private String partId;

    /**
     * 是否大件: 用于厂内管理，暂时用不到
     */
    @TableField(value = "is_large_item")
    private Integer isLargeItem;

    /**
     * 是否外采件: 如果外采件，需要发起采购Call-off；如果自制件，先不做任何动作，自制件生产计划排产功能以后补充完善
     */
    @TableField(value = "is_external_purchase")
    private Integer isExternalPurchase;

    /**
     * 重量: 单位：公斤。暂时用不到，以后作为运输超重判断
     */
    @TableField(value = "weight")
    private Double weight;

    /**
     * 厂内物流模式: 用于厂内拉动，暂时不用
     */
    @TableField(value = "internal_logistics_mode")
    private String internalLogisticsMode;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}