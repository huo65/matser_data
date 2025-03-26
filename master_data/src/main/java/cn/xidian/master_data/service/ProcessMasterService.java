package cn.xidian.master_data.service;

import cn.xidian.master_data.model.entity.ProcessMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

/**
* @author huozj
* @description 针对表【process_master(JIS基座开发完成后，工艺主数据以JIS基座中配置的为准)】的数据库操作Service
* @createDate 2025-03-19 20:33:28
*/
public interface ProcessMasterService extends IMppService<ProcessMaster> {
    List<ProcessMaster> getByPartId(String partId);
}
