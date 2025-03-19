package cn.xidian.master_data.model.dto.logistics;

import cn.xidian.master_data.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 零件主数据以后从PMS获取，暂时先以物流主数据形式配置
 * @author huozj
 * @TableName parts_master
 */
@Data
public class LogisticsMasterQueryRequest extends PageRequest implements Serializable {
    /**
     * 零件号: 零件的唯一识别码
     */
    private String partId;

    /**
     * 零件名称
     */
    private Integer isExternalPurchase;


}