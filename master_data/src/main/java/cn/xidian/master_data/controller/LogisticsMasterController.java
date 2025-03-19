package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.logistics.LogisticsMasterAddRequest;
import cn.xidian.master_data.model.dto.logistics.LogisticsMasterDeleteRequest;
import cn.xidian.master_data.model.dto.logistics.LogisticsMasterQueryRequest;
import cn.xidian.master_data.model.dto.logistics.LogisticsMasterUpdateRequest;
import cn.xidian.master_data.model.entity.LogisticsMaster;
import cn.xidian.master_data.service.LogisticsMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 物流主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/logistics")
@Slf4j
public class LogisticsMasterController {

    @Resource
    private LogisticsMasterService logisticsMasterService;
    
    // region 增删改查

    /**
     * 添加物流主数据信息
     * 创建
     *
     * @param logisticsMasterAddRequest 物流主数据添加请求
     * @return {@link BaseResponse}<{@link Long}>
     */
    @PostMapping
    public BaseResponse<Long> addLogisticsMaster(@RequestBody LogisticsMasterAddRequest logisticsMasterAddRequest) {
        if (logisticsMasterAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LogisticsMaster logisticsMaster = new LogisticsMaster();
        BeanUtils.copyProperties(logisticsMasterAddRequest, logisticsMaster);
        try {
            boolean result = logisticsMasterService.save(logisticsMaster);
        } catch (Exception e) {
            log.error("添加失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("添加成功");
    }

    /**
     * 删除物流主数据通过主键
     *
     * @param id      id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{part_id}")
    public BaseResponse<String> deleteBlogById(@PathVariable("part_id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = logisticsMasterService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除物流主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteLogisticsMaster(@RequestBody LogisticsMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getPartIds()) || deleteRequest.getPartIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = logisticsMasterService.removeByIds(deleteRequest.getPartIds());
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新物流主数据
     *
     * @param logisticsMasterUpdateRequest 物流主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updateLogisticsMaster(@RequestBody LogisticsMasterUpdateRequest logisticsMasterUpdateRequest) {
        if (ObjectUtils.anyNull(logisticsMasterUpdateRequest, logisticsMasterUpdateRequest.getPartId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LogisticsMaster logisticsMaster = new LogisticsMaster();
        BeanUtils.copyProperties(logisticsMasterUpdateRequest, logisticsMaster);
        boolean result = logisticsMasterService.updateById(logisticsMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param logisticsMasterQueryRequest 物流主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  LogisticsMasterAddRequest}>>
     */
    @GetMapping
    public BaseResponse<Page<LogisticsMaster>> getLogisticsMasterByPage(LogisticsMasterQueryRequest logisticsMasterQueryRequest) {
        if (logisticsMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = logisticsMasterQueryRequest.getPageSize();
        long current = logisticsMasterQueryRequest.getCurrent();
        String sortField = logisticsMasterQueryRequest.getSortField();
        String sortOrder = logisticsMasterQueryRequest.getSortOrder();

        String partId = logisticsMasterQueryRequest.getPartId();
//        TODO 确认是这个？
        Integer isExternalPurchase = logisticsMasterQueryRequest.getIsExternalPurchase();
        QueryWrapper<LogisticsMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(isExternalPurchase != null, "is_external_purchase", isExternalPurchase);
        queryWrapper.like(StringUtils.isNotBlank(partId), "part_id", partId);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<LogisticsMaster> logisticsMasterPage = logisticsMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(logisticsMasterPage);
    }


}
