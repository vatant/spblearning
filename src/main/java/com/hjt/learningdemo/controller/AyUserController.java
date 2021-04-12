package com.hjt.learningdemo.controller;

import com.hjt.learningdemo.model.AyUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.AyUserService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ayUser")
public class AyUserController {

    @Resource
    private AyUserService ayUserService;

    @RequestMapping("/test")
    public String test(Model model){
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        return "ayUser";
    }

}
