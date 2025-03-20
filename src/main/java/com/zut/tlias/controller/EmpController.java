package com.zut.tlias.controller;

import com.zut.tlias.pojo.Emp;
import com.zut.tlias.pojo.PageBean;
import com.zut.tlias.pojo.Result;
import com.zut.tlias.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询
    //前端需要将页码和每页显示的个数传递过来
    //前段没传递这两个参数时，默认值是1和10
    @GetMapping
    public Result findByPage(@RequestParam(defaultValue = "1")Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String name, Integer gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){

        PageBean pagebean=empService.findByPage(page,pageSize,name,gender,begin,end);

        return Result.success(pagebean);
    }


    //根据id删除员工
    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("根据id删除员工信息，id为：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }

    //添加员工
    //前端传递过来的是json格式
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工，员工信息为：{}",emp);
        empService.add(emp);
        return Result.success();
    }

    //根据id查询员工
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Emp emp=empService.findById(id);
        return Result.success(emp);
    }


}
