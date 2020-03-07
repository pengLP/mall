package com.lp.mall.service;

import com.lp.mall.bean.PmsBaseAttrInfo;
import com.lp.mall.bean.PmsBaseAttrValue;
import com.lp.mall.bean.PmsBaseSaleAttr;

import java.util.List;

public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();

}
