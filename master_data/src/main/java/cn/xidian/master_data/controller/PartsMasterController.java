package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.PartsMasterAddRequest;
import cn.xidian.master_data.model.dto.PartsMasterDeleteRequest;
import cn.xidian.master_data.model.dto.PartsMasterQueryRequest;
import cn.xidian.master_data.model.dto.PartsMasterUpdateRequest;
import cn.xidian.master_data.model.entity.PartsMaster;
import cn.xidian.master_data.service.PartsMasterService;
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
    
    // region 增删改查

    /**
     * 添加零件主数据信息
     * 创建
     *
     * @param partsMasterAddRequest 零件主数据添加请求
     * @return {@link BaseResponse}<{@link Long}>
     */
    @PostMapping
    public BaseResponse<Long> addPartsMaster(@RequestBody PartsMasterAddRequest partsMasterAddRequest) {
        if (partsMasterAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        PartsMaster partsMaster = new PartsMaster();
        BeanUtils.copyProperties(partsMasterAddRequest, partsMaster);
        try {
            boolean result = partsMasterService.save(partsMaster);
        } catch (Exception e) {
            log.error("添加失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("添加成功");
    }

    /**
     * 删除零件主数据通过主键
     *
     * @param id      id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{part_id}")
    public BaseResponse<String> deleteBlogById(@PathVariable("part_id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
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
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getPartIds()) || deleteRequest.getPartIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = partsMasterService.removeByIds(deleteRequest.getPartIds());
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
