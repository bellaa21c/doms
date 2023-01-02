package com.skbroadband.doms.web.controller;

import com.skbroadband.doms.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.web.controller
 * @File : UserController
 * @Program :
 * @Date : 2022-12-08
 * @Comment :
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "/signup";
    }

    @PostMapping("/signup")
    public String signup(Model model) {
        userService.insert();

        return "redirect:/login";
    }
}
