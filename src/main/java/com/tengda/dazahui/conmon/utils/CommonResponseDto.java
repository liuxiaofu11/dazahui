package com.tengda.dazahui.conmon.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author teswell
 * @Date 2020/12/11 15:35
 * @function
 */
@Data
public class CommonResponseDto  implements Serializable {

    private Integer code;

    private boolean success;

    public CommonResponseDto code(Integer code){
        this.code = code;
        return this;
    }

    public CommonResponseDto success(boolean success){
        this.success = success;
        return this;
    }

}
