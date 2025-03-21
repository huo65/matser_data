package cn.xidian.master_data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xidian.master_data.model.entity.ProcessMaster;
import cn.xidian.master_data.service.ProcessMasterService;
import cn.xidian.master_data.mapper.ProcessMasterMapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author huozj
* @description 针对表【process_master(JIS基座开发完成后，工艺主数据以JIS基座中配置的为准)】的数据库操作Service实现
* @createDate 2025-03-19 20:33:28
*/
@Service
public class ProcessMasterServiceImpl extends MppServiceImpl<ProcessMasterMapper, ProcessMaster>
    implements ProcessMasterService{

}




