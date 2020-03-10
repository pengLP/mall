package com.lp.mall.manage.mapper;

import com.lp.mall.bean.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo>{

    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
