package com.zut.tlias.controller;
import com.zut.tlias.pojo.Dept;
import com.zut.tlias.pojo.Emp;
import com.zut.tlias.pojo.Result;
import com.zut.tlias.service.EmpService;
import com.zut.tlias.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    //根据前端传递过来json的username,password实现登录
    @GetMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp e = empService.findByUsernameAndPassword(emp);
        if(e!=null){
            //允许登录
            Map<String, Object> map=new HashMap<>();
            map.put("username",e.getUsername());
            map.put("id",e.getId());
            map.put("name",e.getName());
            String jwt = JwtUtils.generateJwt(map);
            return Result.success(jwt);
        }

        return Result.err("用户名或密码错误");
    }
}
