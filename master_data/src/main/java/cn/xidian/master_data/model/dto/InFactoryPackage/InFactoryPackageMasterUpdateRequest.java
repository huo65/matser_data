package cn.xidian.master_data.model.dto.InFactoryPackage;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author huozj
 * @TableName in_factory_package_master
 */
@Data
public class InFactoryPackageMasterUpdateRequest implements Serializable {
    private Integer id;
    /**
     * 零件号: 零件的唯一识别码
     */
    private String partId;

    /**
     * 包装类型: 用于描述入厂包装的包装类别
     */
    private String packageType;

    /**
     * SNP: 零件单包装收容数
     */
    private Integer snp;

    /**
     * 长: 包装长度（单位厘米）
     */
    private Integer length;

    /**
     * 宽: 包装长度（单位厘米）
     */
    private Integer width;

    /**
     * 高: 包装长度（单位厘米）
     */
    private Integer height;

    /**
     * 是否入厂翻包: 入厂包装是否满足上线要求
     */
    private Integer isInFactoryRepack;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}