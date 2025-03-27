package cn.xidian.master_data.model.dto.InFactoryPackage;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 零件主数据以后从PMS获取，暂时先以物流主数据形式配置
 * @author huozj
 * @TableName parts_master
 */
@Data
public class InFactoryPackageMasterDeleteRequest implements Serializable {

    private List<Integer> ids;


}