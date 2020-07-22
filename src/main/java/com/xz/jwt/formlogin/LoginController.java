package com.xz.jwt.formlogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Package: com.xz.jwt.formlogin
 * @ClassName: LoginController
 * @Author: xz
 * @Date: 2020/7/22 17:34
 * @Version: 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/error")
    public String error(){
        return "login";
    }
}
