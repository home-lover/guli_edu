package com.guli.common.exception;

import com.guli.common.constants.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api("自定义全局异常类")
public class GuliException extends RuntimeException {

    @ApiModelProperty("状态码")
    private Integer code;

    /**
     * 接受状态码和消息
     * @param code
     * @param message
     */
    public GuliException(Integer code, String message) {
        super(message); //会把参数message付给RuntimeException的成员变量message
        this.code=code;
    }

    /**
     * 接收枚举类型
     * @param resultCodeEnum
     */
    public GuliException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}
