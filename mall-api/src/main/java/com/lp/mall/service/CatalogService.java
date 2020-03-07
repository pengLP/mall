package com.lp.mall.service;

import com.lp.mall.bean.PmsBaseCatalog1;
import com.lp.mall.bean.PmsBaseCatalog2;
import com.lp.mall.bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

    List<PmsBaseCatalog1> getAllPmsBaseCatalog1();


    List<PmsBaseCatalog2> getAllPmsBaseCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getAllPmsBaseCatalog3(String catalog2Id);
}
