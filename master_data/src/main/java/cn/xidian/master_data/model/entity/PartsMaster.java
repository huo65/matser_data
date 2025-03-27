package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 零件主数据以后从PMS获取，暂时先以物流主数据形式配置
 * @TableName parts_master
 */
@TableName(value ="parts_master")
@Data
public class PartsMaster implements Serializable {
    /**
     * 非业务主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 零件号: 零件的唯一识别码
     */
    private String partId;

    /**
     * 零件名称
     */
    private String partName;

    /**
     * 零件英文名称
     */
    private String partEnglishName;

    /**
     * 是否辅料: 标识是否为辅助材料
     */
    private Integer isAuxiliaryMaterial;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}