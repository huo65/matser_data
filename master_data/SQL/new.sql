create database if not exists master_data;

create table if not exists in_factory_package_master
(
    id                     int auto_increment comment '非业务主键ID'
        primary key,
    part_id                varchar(32) not null comment '零件号: 零件的唯一识别码',
    package_type           varchar(32) null comment '包装类型: 用于描述入厂包装的包装类别',
    snp                    int         not null comment 'SNP: 零件单包装收容数',
    length                 int         not null comment '长: 包装长度（单位厘米）',
    width                  int         not null comment '宽: 包装长度（单位厘米）',
    height                 int         not null comment '高: 包装长度（单位厘米）',
    is_in_factory_repack   tinyint(1)  null comment '是否入厂翻包: 入厂包装是否满足上线要求',
    create_time            datetime    null,
    constraint unique_part_id
        unique (part_id)
);

create table if not exists logistics_master
(
    id                      int auto_increment comment '非业务主键ID'
        primary key,
    part_id                 varchar(32) not null comment '零件号: 零件唯一标识',
    is_large_item           tinyint(1)  null comment '是否大件: 用于厂内管理，暂时用不到',
    is_external_purchase    tinyint(1)  not null comment '是否外采件: 如果外采件，需要发起采购Call-off；如果自制件，先不做任何动作，自制件生产计划排产功能以后补充完善',
    weight                  double      null comment '重量: 单位：公斤。暂时用不到，以后作为运输超重判断',
    internal_logistics_mode varchar(32) null comment '厂内物流模式: 用于厂内拉动，暂时不用',
    create_time             datetime    null,
    constraint unique_part_id
        unique (part_id)
);

create table if not exists parts_master
(
    id                     int auto_increment comment '非业务主键ID'
        primary key,
    part_id                varchar(32)  not null comment '零件号: 零件的唯一识别码',
    part_name              varchar(512) not null comment '零件名称',
    part_english_name      varchar(512) null comment '零件英文名称',
    is_auxiliary_material  tinyint(1)   not null comment '是否辅料: 标识是否为辅助材料',
    create_time            datetime     null,
    constraint unique_part_id
        unique (part_id)
)
    comment '零件主数据以后从PMS获取，暂时先以物流主数据形式配置';

create table if not exists process_master
(
    id                      int auto_increment comment '非业务主键ID'
        primary key,
    part_id                 varchar(32) not null comment '零件号：零件唯一标识',
    vehicle_model           varchar(32) not null comment '车型: 每个车型的零件消耗不一样，如果不配置则认为所有车型消耗量相同。也可以以通配符形式简化配置',
    factory                 varchar(32) null comment '工厂: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上',
    workshop                varchar(32) null comment '车间: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上',
    line                    varchar(32) null comment '线体: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上',
    consumption_position    varchar(32) not null comment '消耗工位: 以后需要跟JIS基座里，每个模型的“唯一标识”字段对应上',
    consumption_amount      int         not null comment '消耗用量: 单工位零件消耗量',
    create_time             datetime    null,
    constraint unique_part_vehicle_consumption
        unique (part_id, vehicle_model, consumption_position)
)
    comment 'JIS基座开发完成后，工艺主数据以JIS基座中配置的为准';

create table if not exists procurement_master
(
    id                      int auto_increment comment '非业务主键ID'
        primary key,
    supplier_code           varchar(32) not null comment '供应商代码',
    part_id                 varchar(32) not null comment '零件号：零件唯一标识',
    production_completion_time int         not null comment '生产制成时间: 单位：分钟。此处配置的生产时用于产生Call-Off指令',
    create_time             datetime    null,
    constraint unique_supplier_part
        unique (supplier_code, part_id)
)
    comment '说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准';

create table if not exists supplier_master
(
    id                       int auto_increment comment '非业务主键ID'
        primary key,
    supplier_code            varchar(32)  not null comment '供应商代码',
    supplier_name            varchar(512) not null comment '供应商名称',
    supplier_address         varchar(512) null comment '供应商地址',
    delivery_time            int          not null comment '运输时间: 单位：分钟。此处配置的运输时间非精确时间，用于产生Call-Off指令，不用于产生运输计划',
    in_factory_delivery_mode varchar(32)  not null comment '入厂运输模式',
    delivery_interval_time   int          null comment '送货间隔时间: 单位：分钟',
    create_time              datetime     null,
    constraint unique_supplier_code
        unique (supplier_code)
)
    comment '说明：JIS基座开发完成后，供应商主数据以JIS基座中配置的为准';