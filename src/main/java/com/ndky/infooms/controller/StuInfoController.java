package com.ndky.infooms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-20
 */
@Controller
@RequestMapping("/stuInfo")
public class StuInfoController {

    @GetMapping("/add")
    public String add(){
        return "module/stuinfo/addStuInfo";
    }

}

