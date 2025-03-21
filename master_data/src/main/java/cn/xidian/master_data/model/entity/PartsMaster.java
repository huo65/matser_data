package cn.xidian.master_data.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 零件号: 零件的唯一识别码
     */
    @TableId
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