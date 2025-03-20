package com.zut.tlias.service.impl;

import com.zut.tlias.aop.MyLog;
import com.zut.tlias.dao.DeptMapper;
import com.zut.tlias.pojo.Dept;
import com.zut.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    //根据id删除部门，也需要删除该部门下的员工
    //该注解为事务注解

    @Transactional(rollbackFor = Exception.class ,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
        //删除该部门下的员工
        deptMapper.deleteEmpByDeptId(id);
    }



    //往部门表中插入数据
    //前段传递过来的只是一个部门名称，需要我们在service层中补全余下的属性
    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());


        deptMapper.insert(dept);
    }




    //更新部门
    //前端传递过来的依旧是一个json数据
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }

}
