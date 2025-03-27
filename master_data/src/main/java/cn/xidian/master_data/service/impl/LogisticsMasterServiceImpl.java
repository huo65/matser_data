package cn.xidian.master_data.service.impl;

import cn.xidian.master_data.model.entity.LogisticsMaster;
import cn.xidian.master_data.model.entity.ProcessMaster;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xidian.master_data.service.LogisticsMasterService;
import cn.xidian.master_data.mapper.LogisticsMasterMapper;
import org.springframework.stereotype.Service;

/**
* @author huozj
* @description 针对表【logistics_master】的数据库操作Service实现
* @createDate 2025-03-19 16:49:35
*/
@Service
public class LogisticsMasterServiceImpl extends ServiceImpl<LogisticsMasterMapper, LogisticsMaster>
    implements LogisticsMasterService{

    @Override
    public Object getByPartId(String partId) {
        QueryWrapper<LogisticsMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("part_id", partId);
        return getOne(queryWrapper);
    }
}




