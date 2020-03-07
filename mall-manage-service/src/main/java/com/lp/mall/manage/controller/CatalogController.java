package com.lp.mall.manage.controller;

import com.lp.mall.bean.PmsBaseCatalog1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CatalogController {

    @RequestMapping("getCatalog")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog() {

        return null;
    }
}
