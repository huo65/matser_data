package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.InFactoryPackage.InFactoryPackageMasterAddRequest;
import cn.xidian.master_data.model.dto.InFactoryPackage.InFactoryPackageMasterDeleteRequest;
import cn.xidian.master_data.model.dto.InFactoryPackage.InFactoryPackageMasterQueryRequest;
import cn.xidian.master_data.model.dto.InFactoryPackage.InFactoryPackageMasterUpdateRequest;
import cn.xidian.master_data.model.entity.InFactoryPackageMaster;
import cn.xidian.master_data.service.InFactoryPackageMasterService;
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
 * 入场包装信息主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/inFactoryPackage")
@Slf4j
public class InFactoryPackageMasterController {

    @Resource
    private InFactoryPackageMasterService inFactoryTransportPackageMasterService;
    @Resource
    private PartsMasterService partsMasterService;
    
    // region 增删改查

    /**
     * 添加入场包装信息主数据信息
     * 创建
     *
     * @param inFactoryTransportPackageMasterAddRequest 入场包装信息主数据添加请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping
    public BaseResponse<Boolean> addInFactoryPackageMaster(@RequestBody InFactoryPackageMasterAddRequest inFactoryTransportPackageMasterAddRequest) {
        if (inFactoryTransportPackageMasterAddRequest == null ||inFactoryTransportPackageMasterAddRequest.getPartId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (partsMasterService.getById(inFactoryTransportPackageMasterAddRequest.getPartId()) == null){
            throw new BusinessException(ErrorCode.PART_ID_ERROR);
        }
        InFactoryPackageMaster inFactoryTransportPackageMaster = new InFactoryPackageMaster();
        BeanUtils.copyProperties(inFactoryTransportPackageMasterAddRequest, inFactoryTransportPackageMaster);
        if (inFactoryTransportPackageMasterService.getById(inFactoryTransportPackageMaster) != null){
            throw new BusinessException(ErrorCode.REPEAT_ERROR);
        }
        boolean result;
        try {
            result = inFactoryTransportPackageMasterService.save(inFactoryTransportPackageMaster);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(result,"添加成功");
    }

    /**
     * 删除入场包装信息主数据通过主键
     *
     * @param id      id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{id}")
    public BaseResponse<String> deleteBlogById(@PathVariable("id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = inFactoryTransportPackageMasterService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除入场包装信息主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInFactoryPackageMaster(@RequestBody InFactoryPackageMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getIds()) || deleteRequest.getIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = inFactoryTransportPackageMasterService.removeByIds(deleteRequest.getIds());
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新入场包装信息主数据
     *
     * @param inFactoryTransportPackageMasterUpdateRequest 入场包装信息主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updateInFactoryPackageMaster(@RequestBody InFactoryPackageMasterUpdateRequest inFactoryTransportPackageMasterUpdateRequest) {
        if (ObjectUtils.anyNull(inFactoryTransportPackageMasterUpdateRequest, inFactoryTransportPackageMasterUpdateRequest.getPartId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InFactoryPackageMaster inFactoryTransportPackageMaster = new  InFactoryPackageMaster();
        BeanUtils.copyProperties(inFactoryTransportPackageMasterUpdateRequest, inFactoryTransportPackageMaster);
        if (inFactoryTransportPackageMasterService.getById(inFactoryTransportPackageMaster) == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        boolean result = inFactoryTransportPackageMasterService.updateById(inFactoryTransportPackageMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param inFactoryTransportPackageMasterQueryRequest 入场包装信息主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  InFactoryPackageMaster}>>
     */
    @GetMapping
    public BaseResponse<Page< InFactoryPackageMaster>> getInFactoryPackageMasterByPage(InFactoryPackageMasterQueryRequest inFactoryTransportPackageMasterQueryRequest) {
        if (inFactoryTransportPackageMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = inFactoryTransportPackageMasterQueryRequest.getPageSize();
        long current = inFactoryTransportPackageMasterQueryRequest.getCurrent();
        String sortField = inFactoryTransportPackageMasterQueryRequest.getSortField();
        String sortOrder = inFactoryTransportPackageMasterQueryRequest.getSortOrder();

        String partId = inFactoryTransportPackageMasterQueryRequest.getPartId();
        String packageType = inFactoryTransportPackageMasterQueryRequest.getPackageType();

        QueryWrapper< InFactoryPackageMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(partId), "part_id", partId)
                .like(StringUtils.isNotBlank(packageType), "package_type", packageType);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page< InFactoryPackageMaster> inFactoryTransportPackageMasterPage = inFactoryTransportPackageMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(inFactoryTransportPackageMasterPage);
    }


}
