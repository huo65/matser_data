package cn.xidian.master_data.model.dto.supplier;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准
 * @author huozj
 */
@TableName(value ="supplier_master")
@Data
public class SupplierMasterDeleteRequest implements Serializable {
    /**
     * 供应商代码
     */
    private List<String> supplierCodes;

}