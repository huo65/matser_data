package cn.xidian.master_data.model.dto.logistics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author huozj
 * @TableName logistics_master
 */

@Data
public class LogisticsMasterAddRequest implements Serializable {
    /**
     * 零件号: 零件唯一标识
     */
    private String partId;

    /**
     * 是否大件: 用于厂内管理，暂时用不到
     */
    private Integer isLargeItem;

    /**
     * 是否外采件: 如果外采件，需要发起采购Call-off；如果自制件，先不做任何动作，自制件生产计划排产功能以后补充完善
     */
    private Integer isExternalPurchase;

    /**
     * 重量: 单位：公斤。暂时用不到，以后作为运输超重判断
     */
    private Double weight;

    /**
     * 厂内物流模式: 用于厂内拉动，暂时不用
     */
    private String internalLogisticsMode;

}