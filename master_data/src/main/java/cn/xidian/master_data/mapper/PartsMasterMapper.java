package cn.xidian.master_data.mapper;

import cn.xidian.master_data.model.entity.PartsMaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author huozj
* @description 针对表【parts_master(零件主数据以后从PMS获取，暂时先以物流主数据形式配置)】的数据库操作Mapper
* @createDate 2025-03-15 23:11:37
* @Entity cn.xidian.master_data.model.entity.PartsMaster
*/
public interface PartsMasterMapper extends BaseMapper<PartsMaster> {

}




