package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.procurement.ProcurementMasterAddRequest;
import cn.xidian.master_data.model.dto.procurement.ProcurementMasterDeleteRequest;
import cn.xidian.master_data.model.dto.procurement.ProcurementMasterQueryRequest;
import cn.xidian.master_data.model.dto.procurement.ProcurementMasterUpdateRequest;
import cn.xidian.master_data.model.entity.ProcurementMaster;
import cn.xidian.master_data.service.ProcurementMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 采购主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/procurement")
@Slf4j
public class ProcurementMasterController {

    @Resource
    private ProcurementMasterService procurementMasterService;
    
    // region 增删改查

    /**
     * 添加采购主数据信息
     * 创建
     *
     * @param procurementMasterAddRequest 采购主数据添加请求
     * @return {@link BaseResponse}<{@link Long}>
     */
    @PostMapping
    public BaseResponse<Long> addProcurementMaster(@RequestBody ProcurementMasterAddRequest procurementMasterAddRequest) {
        if (procurementMasterAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProcurementMaster procurementMaster = new ProcurementMaster();
        BeanUtils.copyProperties(procurementMasterAddRequest, procurementMaster);
        try {
            boolean result = procurementMasterService.save(procurementMaster);
        } catch (Exception e) {
            log.error("添加失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("添加成功");
    }

    /**
     * 删除采购主数据通过主键
     *
     * @param partId    partId
     * @param supplierCode    supplierCode
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{PartId}/{SupplierCode}")
    public BaseResponse<String> deleteBlogById(@PathVariable("PartId") String partId, @PathVariable("SupplierCode") String supplierCode) {
        if (partId == null || supplierCode == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProcurementMaster procurementMaster = new ProcurementMaster();
        procurementMaster.setPartId(partId);
        procurementMaster.setSupplierCode(supplierCode);
        boolean result = procurementMasterService.deleteByMultiId(procurementMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除采购主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProcurementMaster(@RequestBody ProcurementMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getIds()) || deleteRequest.getIds().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        try {
            for (ProcurementMasterDeleteRequest.Pair<String, String> pair : deleteRequest.getIds()){
                ProcurementMaster procurementMaster = new ProcurementMaster();
                procurementMaster.setPartId(pair.getPartId());
                procurementMaster.setSupplierCode(pair.getSupplierCode());
                procurementMasterService.deleteByMultiId(procurementMaster);
            }
        } catch (Exception e) {
            log.error("批量删除失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新采购主数据
     *
     * @param procurementMasterUpdateRequest 采购主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updateProcurementMaster(@RequestBody ProcurementMasterUpdateRequest procurementMasterUpdateRequest) {
        if (ObjectUtils.anyNull(procurementMasterUpdateRequest, procurementMasterUpdateRequest.getPartId(), procurementMasterUpdateRequest.getSupplierCode())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//        TODO 修改不能涉及主键
        ProcurementMaster procurementMaster = new ProcurementMaster();
        BeanUtils.copyProperties(procurementMasterUpdateRequest, procurementMaster);
        boolean result = procurementMasterService.updateByMultiId(procurementMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param procurementMasterQueryRequest 采购主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  ProcurementMasterAddRequest}>>
     */
    @GetMapping
    public BaseResponse<Page<ProcurementMaster>> getProcurementMasterByPage(ProcurementMasterQueryRequest procurementMasterQueryRequest) {
        if (procurementMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = procurementMasterQueryRequest.getPageSize();
        long current = procurementMasterQueryRequest.getCurrent();
        String sortField = procurementMasterQueryRequest.getSortField();
        String sortOrder = procurementMasterQueryRequest.getSortOrder();

        String partId = procurementMasterQueryRequest.getPartId();
        String supplierCode = procurementMasterQueryRequest.getSupplierCode();
        QueryWrapper<ProcurementMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(partId), "part_id", partId)
                        .like(StringUtils.isNotBlank(supplierCode), "supplier_code", supplierCode);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ProcurementMaster> procurementMasterPage = procurementMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(procurementMasterPage);
    }


}
