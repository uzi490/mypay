package com.mx.service.controller;

import com.mx.service.entity.TUser;
import com.mx.service.service.LoginService;
import com.mx.service.utils.JwtUtils;
import com.mx.service.vo.resp.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: mx-service
 * @Package: com.mx.service.controller
 * @Description: note
 * @Author: changfx43807
 * @CreateDate: 2023/8/3 17:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright © 2023 Hundsun Technologies Inc. All Rights Reserved
 **/
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/login")
    public ApiResult login(@RequestBody TUser tUser){
        return loginService.userLogin(tUser);
    }

    @RequestMapping("/freeLogin")
    public String freeLogin(){
        String token = jwtUtils.createToken("10003");
        return token;
    }

}
