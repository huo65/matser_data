package cn.xidian.master_data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xidian.master_data.model.entity.InFactoryPackageMaster;
import cn.xidian.master_data.service.InFactoryPackageMasterService;
import cn.xidian.master_data.mapper.InFactoryPackageMasterMapper;
import org.springframework.stereotype.Service;

/**
* @author huozj
* @description 针对表【in_factory_package_master】的数据库操作Service实现
* @createDate 2025-03-19 20:38:38
*/
@Service
public class InFactoryPackageMasterServiceImpl extends ServiceImpl<InFactoryPackageMasterMapper, InFactoryPackageMaster>
    implements InFactoryPackageMasterService{

    @Override
    public Object getByPartId(String partId) {
        QueryWrapper<InFactoryPackageMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("part_id", partId);
        return getOne(queryWrapper);
    }
}




