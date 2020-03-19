package com.lp.mall.service;


import com.lp.mall.bean.PmsSearchParam;
import com.lp.mall.bean.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}
