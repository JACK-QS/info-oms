package com.ndky.infooms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenqingsheng
 * @date 2021/1/25 13:51
 */
@Controller
@RequestMapping("/devUtils")
public class DevUtilsController {

    @GetMapping("/menuIcon")
    public String menuIcon() {
        return "module/devutils/menuIcon";
    }

    @GetMapping("/vCharts")
    public String vcharts() {
        return "module/devutils/vCharts";
    }
}
