package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.process.ProcessMasterAddRequest;
import cn.xidian.master_data.model.dto.process.ProcessMasterDeleteRequest;
import cn.xidian.master_data.model.dto.process.ProcessMasterQueryRequest;
import cn.xidian.master_data.model.dto.process.ProcessMasterUpdateRequest;
import cn.xidian.master_data.model.entity.ProcessMaster;
import cn.xidian.master_data.service.PartsMasterService;
import cn.xidian.master_data.service.ProcessMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 工艺主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/process")
@Slf4j
public class ProcessMasterController {

    @Resource
    private ProcessMasterService processMasterService;
    @Resource
    private PartsMasterService partsMasterService;
    
    // region 增删改查

    /**
     * 添加工艺主数据信息
     * 创建
     *
     * @param processMasterAddRequest 工艺主数据添加请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping
    public BaseResponse<Boolean> addProcessMaster(@RequestBody ProcessMasterAddRequest processMasterAddRequest) {
        if (ObjectUtils.anyNull(processMasterAddRequest, processMasterAddRequest.getPartId(), processMasterAddRequest.getVehicleModel(), processMasterAddRequest.getConsumptionPosition())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (partsMasterService.getByPartId(processMasterAddRequest.getPartId()) == null){
            throw new BusinessException(ErrorCode.PART_ID_ERROR);
        }
        ProcessMaster processMaster = new ProcessMaster();
        BeanUtils.copyProperties(processMasterAddRequest, processMaster);
        boolean result;
        try {
            result = processMasterService.save(processMaster);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(result,"添加成功");
    }

    /**
     * 删除工艺主数据通过主键
     *
     * @param id    id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{id}")
    public BaseResponse<String> deleteBlogById(@PathVariable("id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        try{
            processMasterService.removeById(id);
        }catch (Exception e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除工艺主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProcessMaster(@RequestBody ProcessMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getIds()) || deleteRequest.getIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        try {
            for (Integer id : deleteRequest.getIds()){
                processMasterService.removeById(id);
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新工艺主数据
     *
     * @param processMasterUpdateRequest 工艺主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updateProcessMaster(@RequestBody ProcessMasterUpdateRequest processMasterUpdateRequest) {
        if (ObjectUtils.anyNull(processMasterUpdateRequest, processMasterUpdateRequest.getId(), processMasterUpdateRequest.getPartId(), processMasterUpdateRequest.getVehicleModel(), processMasterUpdateRequest.getConsumptionPosition())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProcessMaster processMaster = new ProcessMaster();
        BeanUtils.copyProperties(processMasterUpdateRequest, processMaster);
        if (processMasterService.getById(processMaster.getId()) == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (partsMasterService.getByPartId(processMasterUpdateRequest.getPartId()) == null){
            throw new BusinessException(ErrorCode.PART_ID_ERROR);
        }
        boolean result = processMasterService.updateById(processMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param processMasterQueryRequest 工艺主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  ProcessMasterAddRequest}>>
     */
    @GetMapping
    public BaseResponse<Page<ProcessMaster>> getProcessMasterByPage(ProcessMasterQueryRequest processMasterQueryRequest) {
        if (processMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = processMasterQueryRequest.getPageSize();
        long current = processMasterQueryRequest.getCurrent();
        String sortField = processMasterQueryRequest.getSortField();
        String sortOrder = processMasterQueryRequest.getSortOrder();

        String partId = processMasterQueryRequest.getPartId();
        String consumptionPosition = processMasterQueryRequest.getConsumptionPosition();
        QueryWrapper<ProcessMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(partId), "part_id", partId)
                        .like(StringUtils.isNotBlank(consumptionPosition), "consumption_position", consumptionPosition);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ProcessMaster> processMasterPage = processMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(processMasterPage);
    }


}
