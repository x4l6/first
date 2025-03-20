package com.zut.tlias.pojo;

import com.zut.tlias.controller.EmpController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private List<Emp> list;
    private Long total;
}
