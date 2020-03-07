package com.lp.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lp.mall.bean.PmsBaseCatalog1;
import com.lp.mall.bean.PmsBaseCatalog2;
import com.lp.mall.bean.PmsBaseCatalog3;
import com.lp.mall.manage.mapper.PmsBaseCatalog1Mapper;
import com.lp.mall.manage.mapper.PmsBaseCatalog2Mapper;
import com.lp.mall.manage.mapper.PmsBaseCatalog3Mapper;
import com.lp.mall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getAllPmsBaseCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getAllPmsBaseCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);

        return pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
    }

    @Override
    public List<PmsBaseCatalog3> getAllPmsBaseCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);

        return pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
    }


}
