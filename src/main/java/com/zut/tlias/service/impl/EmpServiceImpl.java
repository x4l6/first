package com.zut.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.tlias.dao.EmpMapper;
import com.zut.tlias.pojo.Emp;
import com.zut.tlias.pojo.PageBean;
import com.zut.tlias.service.EmpService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //分页查询，后端要给前端返回指定页码中的数据，数据总数
    //后端把这些数据封装到PageBean对象中，再返回给前端

    //原版的分页查询
    /*@Override
    public PageBean findByPage(Integer page, Integer pageSize) {
        Long total=empMapper.count();

        //每页的起始索引
        Integer start=(page-1)*pageSize;
        List<Emp> empList= empMapper.page(start,pageSize);
        PageBean pb=new PageBean(empList,total);
        return pb;
    }*/


    //pageHelper分页插件帮助下的分页查询
    @Override
    public PageBean findByPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin,LocalDate end) {
        //参数一：页码
        //参数二：每页显示的数据个数
        PageHelper.startPage(page,pageSize);
        Integer start=(page-1)*pageSize;

        List<Emp> empList = empMapper.page(start, pageSize,name,gender,begin,end);
        //获取分页后的数据，并将其强转为page类型
        Page<Emp> p = (Page<Emp>) empList;
        PageBean pb=new PageBean(p.getResult(),p.getTotal());
        return pb;
    }


    @Override
    public void deleteById(List<Integer> ids) {
        empMapper.deleteById(ids);

    }

    @Override
    public void add(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());

        empMapper.add(emp);
    }

    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        return emp;
    }

    @Override
    public Emp findByUsernameAndPassword(Emp emp) {

        Emp e= empMapper.findByUsernameAndPassword(emp);
        return e;
    }


}
