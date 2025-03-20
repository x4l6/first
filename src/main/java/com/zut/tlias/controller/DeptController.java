package com.zut.tlias.controller;

import com.zut.tlias.pojo.Dept;
import com.zut.tlias.pojo.Result;
import com.zut.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //生成日记记录对象
    //下行代码的作用和   @Slf4j注释的效果一致
    //private static Logger log= LoggerFactory.getLogger( DeptController.class);

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        //log.info("查询所有部门信息。你看我在控制台上打印成功了吗");
        List<Dept> all = deptService.findAll();
        return Result.success(all);
    }


    //根据id删除部门信息
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除部门信息，id为：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门信息
    //前端传递过来的json格式的数据，需要将其封装到实体类中，在形参前加入@RequestBody注解
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        deptService.insert(dept);
        return Result.success();
    }


    //更新部门信息
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门信息，传递的参数为：{}",dept);
        deptService.update(dept);
        return Result.success(dept);
    }





}
