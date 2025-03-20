package com.zut.tlias.dao;

import com.zut.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);


    // 查询所有部门信息
    @Select("select * from dept")
    List<Dept> findAll();


    // 添加部门
    @Insert("insert into dept ( name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    // 更新部门
    @Update("update dept set name =#{name} where id in ()")
    void update(Dept dept);

    // 根据部门id删除员工
    @Delete("delete from emp where dept_id = #{id}")
    void deleteEmpByDeptId(Integer id);
}
