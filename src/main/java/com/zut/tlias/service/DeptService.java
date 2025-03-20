package com.zut.tlias.service;

import com.zut.tlias.pojo.Dept;
import com.zut.tlias.pojo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
     //查询所有部门信息
     List<Dept> findAll();
     
     //根据部门删除元素
     void deleteById(Integer id);


     //添加部门
     void insert(Dept dept);


     //更新部门
     void update(Dept dept);
}
