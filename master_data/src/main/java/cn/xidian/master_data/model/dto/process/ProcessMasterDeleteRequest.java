package cn.xidian.master_data.model.dto.process;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 零件主数据以后从PMS获取，暂时先以物流主数据形式配置
 * @author huozj
 * @TableName parts_master
 */
@Data
public class ProcessMasterDeleteRequest implements Serializable {

    private List<Pair<String,String,String>> ids;
    @Data
    public static class Pair<A, B, C> {
        private A partId;
        private B vehicleModel;
        private C consumptionPosition;
    }
}