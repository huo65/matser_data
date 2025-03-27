package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.parts.PartsMasterAddRequest;
import cn.xidian.master_data.model.dto.parts.PartsMasterDeleteRequest;
import cn.xidian.master_data.model.dto.parts.PartsMasterQueryRequest;
import cn.xidian.master_data.model.dto.parts.PartsMasterUpdateRequest;
import cn.xidian.master_data.model.entity.PartsMaster;
import cn.xidian.master_data.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 零件主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/parts")
@Slf4j
public class PartsMasterController {

    @Resource
    private PartsMasterService partsMasterService;
    @Resource
    private ProcessMasterService processMasterService;
    @Resource
    private LogisticsMasterService logisticsMasterService;
    @Resource
    private ProcurementMasterService procurementMasterService;
    @Resource
    private InFactoryPackageMasterService inFactoryPackageMasterService;

    
    // region 增删改查

    /**
     * 添加零件主数据信息
     * 创建
     *
     * @param partsMasterAddRequest 零件主数据添加请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping
    public BaseResponse<Boolean> addPartsMaster(@RequestBody PartsMasterAddRequest partsMasterAddRequest) {
        if (partsMasterAddRequest == null || partsMasterAddRequest.getPartId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (partsMasterAddRequest.getPartId().length() != 10 || !StringUtils.isNumeric(partsMasterAddRequest.getPartId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        PartsMaster partsMaster = new PartsMaster();
        BeanUtils.copyProperties(partsMasterAddRequest, partsMaster);
        if (partsMasterService.getById(partsMaster) != null){
            throw new BusinessException(ErrorCode.REPEAT_ERROR);
        }
        boolean result;
        try {
            result = partsMasterService.save(partsMaster);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(result,"添加成功");
    }

    /**
     * 删除零件主数据通过主键
     *
     * @param id      id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{id}")
    public BaseResponse<String> deleteBlogById(@PathVariable("id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String partId = partsMasterService.getById(id).getPartId();
        if (logisticsMasterService.getByPartId(partId) != null ||  inFactoryPackageMasterService.getByPartId(partId) != null || !processMasterService.getByPartId(partId).isEmpty() || !procurementMasterService.getByPartId(partId).isEmpty()){
            throw new BusinessException(ErrorCode.PART_CONFIGURED);
        }

        boolean result = partsMasterService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除零件主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePartsMaster(@RequestBody PartsMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getIds()) || deleteRequest.getIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        for (Integer id : deleteRequest.getIds()){
            if (partsMasterService.getById(id) == null){
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
            }
            String partId = partsMasterService.getById(id).getPartId();
            if (logisticsMasterService.getByPartId(partId) != null ||  inFactoryPackageMasterService.getByPartId(partId) != null || !processMasterService.getByPartId(partId).isEmpty() || !procurementMasterService.getByPartId(partId).isEmpty()){
                throw new BusinessException(ErrorCode.PART_CONFIGURED);
            }
        }
        boolean result = partsMasterService.removeByIds(deleteRequest.getIds());
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新零件主数据
     *
     * @param partsMasterUpdateRequest 零件主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updatePartsMaster(@RequestBody PartsMasterUpdateRequest partsMasterUpdateRequest) {
        if (ObjectUtils.anyNull(partsMasterUpdateRequest, partsMasterUpdateRequest.getPartId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        PartsMaster partsMaster = new  PartsMaster();
        BeanUtils.copyProperties(partsMasterUpdateRequest, partsMaster);
        if (partsMasterService.getById(partsMaster) == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (partsMasterUpdateRequest.getPartId().length() != 10 || !StringUtils.isNumeric(partsMasterUpdateRequest.getPartId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String partId = partsMasterService.getById(partsMaster).getPartId();
        if (logisticsMasterService.getByPartId(partId) != null ||  inFactoryPackageMasterService.getByPartId(partId) != null || !processMasterService.getByPartId(partId).isEmpty() || !procurementMasterService.getByPartId(partId).isEmpty()){
            throw new BusinessException(ErrorCode.PART_CONFIGURED);
        }
        boolean result = partsMasterService.updateById(partsMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param partsMasterQueryRequest 零件主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  PartsMaster}>>
     */
    @GetMapping
    public BaseResponse<Page< PartsMaster>> getPartsMasterByPage(PartsMasterQueryRequest partsMasterQueryRequest) {
        if (partsMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = partsMasterQueryRequest.getPageSize();
        long current = partsMasterQueryRequest.getCurrent();
        String sortField = partsMasterQueryRequest.getSortField();
        String sortOrder = partsMasterQueryRequest.getSortOrder();

        String partId = partsMasterQueryRequest.getPartId();
        String partName = partsMasterQueryRequest.getPartName();

        QueryWrapper< PartsMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(partId), "part_id", partId)
                .like(StringUtils.isNotBlank(partName), "part_name", partName);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page< PartsMaster> partsMasterPage = partsMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(partsMasterPage);
    }


}
