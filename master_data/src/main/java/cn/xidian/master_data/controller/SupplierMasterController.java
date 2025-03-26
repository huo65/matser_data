package cn.xidian.master_data.controller;

import cn.xidian.master_data.common.BaseResponse;
import cn.xidian.master_data.common.CommonConstant;
import cn.xidian.master_data.common.ErrorCode;
import cn.xidian.master_data.common.ResultUtils;
import cn.xidian.master_data.exception.BusinessException;
import cn.xidian.master_data.model.dto.supplier.SupplierMasterAddRequest;
import cn.xidian.master_data.model.dto.supplier.SupplierMasterDeleteRequest;
import cn.xidian.master_data.model.dto.supplier.SupplierMasterQueryRequest;
import cn.xidian.master_data.model.dto.supplier.SupplierMasterUpdateRequest;
import cn.xidian.master_data.model.entity.SupplierMaster;
import cn.xidian.master_data.service.ProcurementMasterService;
import cn.xidian.master_data.service.SupplierMasterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商主数据接口
 *
 * @author huozj
 */
@RestController
@RequestMapping("/supplier")
@Slf4j
public class SupplierMasterController {

    @Resource
    private SupplierMasterService supplierMasterService;
    @Resource
    private ProcurementMasterService procurementMasterService;
    
    // region 增删改查

    /**
     * 添加供应商主数据信息
     * 创建
     *
     * @param supplierMasterAddRequest 供应商主数据添加请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping
    public BaseResponse<Boolean> addSupplierMaster(@RequestBody SupplierMasterAddRequest supplierMasterAddRequest) {
        if (ObjectUtils.anyNull(supplierMasterAddRequest, supplierMasterAddRequest.getSupplierCode())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (supplierMasterAddRequest.getSupplierCode().length() != 10 || !StringUtils.isNumeric(supplierMasterAddRequest.getSupplierCode())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SupplierMaster supplierMaster = new SupplierMaster();
        BeanUtils.copyProperties(supplierMasterAddRequest, supplierMaster);
        if (supplierMasterService.getById(supplierMaster) != null){
            throw new BusinessException(ErrorCode.REPEAT_ERROR);
        }
        boolean result;
        try {
            result = supplierMasterService.save(supplierMaster);
        } catch (Exception e) {
            log.error("添加失败", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        return ResultUtils.success(result,"添加成功");
    }

    /**
     * 删除供应商主数据通过主键
     *
     * @param id      id
     * @return {@link BaseResponse}<{@link String}>
     */
    @DeleteMapping("{supplier_code}")
    public BaseResponse<String> deleteBlogById(@PathVariable("supplier_code") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (procurementMasterService.getBySupplierCode(id) != null){
            throw new BusinessException(ErrorCode.SUPPLIER_CONFIGURED);
        }
        boolean result = supplierMasterService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("删除成功");
    }
    /**
     * 批量删除供应商主数据信息
     *
     * @param deleteRequest 删除请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteSupplierMaster(@RequestBody SupplierMasterDeleteRequest deleteRequest) {
        if(ObjectUtils.anyNull(deleteRequest, deleteRequest.getSupplierCodes()) || deleteRequest.getSupplierCodes().isEmpty() ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        for (String id : deleteRequest.getSupplierCodes()){
            if (procurementMasterService.getBySupplierCode(id) != null){
                throw new BusinessException(ErrorCode.SUPPLIER_CONFIGURED);
            }
        }
        boolean result = supplierMasterService.removeByIds(deleteRequest.getSupplierCodes());
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("批量删除成功");
    }

    /**
     * 更新供应商主数据
     *
     * @param supplierMasterUpdateRequest 供应商主数据更新请求 
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PutMapping
    public BaseResponse<Boolean> updateSupplierMaster(@RequestBody SupplierMasterUpdateRequest supplierMasterUpdateRequest) {
        if (ObjectUtils.anyNull(supplierMasterUpdateRequest, supplierMasterUpdateRequest.getSupplierCode())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SupplierMaster supplierMaster = new SupplierMaster();
        BeanUtils.copyProperties(supplierMasterUpdateRequest, supplierMaster);
       if (supplierMasterService.getById(supplierMaster) == null){
           throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
       }
        boolean result = supplierMasterService.updateById(supplierMaster);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success("修改成功");
    }

    /**
     * 分页获取列表
     * @param supplierMasterQueryRequest 供应商主数据查询请求     请求
     * @return {@link BaseResponse}<{@link Page}<{@link  SupplierMasterAddRequest}>>
     */
    @GetMapping
    public BaseResponse<Page<SupplierMaster>> getSupplierMasterByPage(SupplierMasterQueryRequest supplierMasterQueryRequest) {
        if (supplierMasterQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long size = supplierMasterQueryRequest.getPageSize();
        long current = supplierMasterQueryRequest.getCurrent();
        String sortField = supplierMasterQueryRequest.getSortField();
        String sortOrder = supplierMasterQueryRequest.getSortOrder();

        String supplierCode = supplierMasterQueryRequest.getSupplierCode();
        String supplierName = supplierMasterQueryRequest.getSupplierName();
        QueryWrapper<SupplierMaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(supplierCode), "supplier_code", supplierCode)
                        .like(StringUtils.isNotBlank(supplierName), "supplier_name", supplierName);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<SupplierMaster> supplierMasterPage = supplierMasterService.page(new Page<>(current, size), queryWrapper);

        return ResultUtils.success(supplierMasterPage);
    }


}
