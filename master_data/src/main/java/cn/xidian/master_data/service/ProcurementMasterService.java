package cn.xidian.master_data.service;

import cn.xidian.master_data.model.entity.ProcurementMaster;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
* @author huozj
* @description 针对表【procurement_master(说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准)】的数据库操作Service
* @createDate 2025-03-19 19:23:28
*/
public interface ProcurementMasterService extends IService<ProcurementMaster> {
    List<ProcurementMaster> getByPartId(String partId);

    List<ProcurementMaster> getBySupplierCode(String supplierCode);
}
