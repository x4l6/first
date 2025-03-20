package com.zut.tlias.service;

import com.zut.tlias.aop.MyLog;
import com.zut.tlias.pojo.Emp;
import com.zut.tlias.pojo.PageBean;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
    PageBean findByPage(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);


    void deleteById(List<Integer> ids);

    void add(Emp emp);

    Emp findById(Integer id);

    Emp findByUsernameAndPassword(Emp emp);
}
