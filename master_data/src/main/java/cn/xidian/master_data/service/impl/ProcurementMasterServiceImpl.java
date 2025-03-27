package cn.xidian.master_data.service.impl;

import cn.xidian.master_data.model.entity.ProcessMaster;
import cn.xidian.master_data.model.entity.ProcurementMaster;
import cn.xidian.master_data.service.ProcurementMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.xidian.master_data.mapper.ProcurementMasterMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
* @author huozj
* @description 针对表【procurement_master(说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准)】的数据库操作Service实现
* @createDate 2025-03-19 19:23:28
*/
@Service
public class ProcurementMasterServiceImpl extends ServiceImpl<ProcurementMasterMapper, ProcurementMaster>
    implements ProcurementMasterService{
    @Resource
    private ProcurementMasterMapper procurementMasterMapper;

    @Override
    public List<ProcurementMaster> getByPartId(String partId) {
        QueryWrapper<ProcurementMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("part_id", partId);
        return list(queryWrapper);
    }

    @Override
    public List<ProcurementMaster> getBySupplierCode(String supplierCode) {
        QueryWrapper<ProcurementMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplier_code", supplierCode);
        return list(queryWrapper);
    }
}




