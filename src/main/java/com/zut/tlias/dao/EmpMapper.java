package com.zut.tlias.dao;

import com.zut.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {


    //分页查询时返回数据的个数
    //原版的分页查询
    /*@Select("select count(*) from emp")
    Long count();
    @Select("select * from emp limit #{start},#{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);
*/

    //@Select("select * from emp")
    List<Emp> page(Integer start, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    void deleteById(List<Integer>  ids);

    void add(Emp emp);

    Emp findById(Integer id);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp findByUsernameAndPassword(Emp emp);


}
