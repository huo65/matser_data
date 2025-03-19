package cn.xidian.master_data.model.dto.procurement;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * 零件主数据以后从PMS获取，暂时先以物流主数据形式配置
 * @author huozj
 * @TableName parts_master
 */
@Data
public class ProcurementMasterDeleteRequest implements Serializable {

    private List<Pair<String,String>> ids;
    @Data
    public static class Pair<K, V> {
        private K partId;
        private V supplierCode;
    }
}