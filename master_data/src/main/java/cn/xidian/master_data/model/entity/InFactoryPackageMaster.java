package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 
 * @author huozj
 * @TableName in_factory_package_master
 */
@Data
@TableName(value ="in_factory_package_master")
public class InFactoryPackageMaster implements Serializable {
    /**
     * 非业务主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 零件号: 零件的唯一识别码
     */
    @TableField(value = "part_id")
    private String partId;

    /**
     * 包装类型: 用于描述入厂包装的包装类别
     */
    @TableField(value = "package_type")
    private String packageType;

    /**
     * SNP: 零件单包装收容数
     */
    @TableField(value = "snp")
    private Integer snp;

    /**
     * 长: 包装长度（单位厘米）
     */
    @TableField(value = "length")
    private Integer length;

    /**
     * 宽: 包装长度（单位厘米）
     */
    @TableField(value = "width")
    private Integer width;

    /**
     * 高: 包装长度（单位厘米）
     */
    @TableField(value = "height")
    private Integer height;

    /**
     * 是否入厂翻包: 入厂包装是否满足上线要求
     */
    @TableField(value = "is_in_factory_repack")
    private Integer isInFactoryRepack;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}