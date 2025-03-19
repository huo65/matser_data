package cn.xidian.master_data.service.impl;

import cn.xidian.master_data.mapper.PartsMasterMapper;
import cn.xidian.master_data.model.entity.PartsMaster;
import cn.xidian.master_data.service.PartsMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author huozj
* @description 针对表【parts_master(零件主数据以后从PMS获取，暂时先以物流主数据形式配置)】的数据库操作Service实现
* @createDate 2025-03-15 23:11:37
*/
@Service
public class PartsMasterServiceImpl extends ServiceImpl<PartsMasterMapper, PartsMaster>
    implements PartsMasterService {

}




