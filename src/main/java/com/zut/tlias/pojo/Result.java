package com.zut.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    // 响应码
    // 1代表成功
    // 0代表失败
    private Integer code;
    // 响应信息
    private String msg;
    //返回的数据
    private Object data;


    public static Result success(){
        return new Result(1,"success",null);
    }

    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result err(String msg){
        return new Result(0,msg,null);
    }


}
