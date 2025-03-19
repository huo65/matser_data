package cn.xidian.master_data.model.dto.InFactoryPackage;

import cn.xidian.master_data.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
@Data
public class InFactoryPackageMasterQueryRequest extends PageRequest implements Serializable {
    /**
     * 零件号: 零件的唯一识别码
     */
    private String partId;

    /**
     * 包装类型: 用于描述入厂包装的包装类别
     */
    private String packageType;

}
