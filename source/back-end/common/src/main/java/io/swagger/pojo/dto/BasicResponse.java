package io.swagger.pojo.dto;

import lombok.Data;

/**
 * 通用返回值，内部平台使用
 */
@Data
public class BasicResponse {
    // todo 状态码基本上就两个就行，接口编码没问题的话不会出现error的
    // todo 其他http已经定义的错误就用http状态码返回
    public static String SUCCESSCODE="ok";
    public static String ERRORCODE="error";
    private String code;
    private Object data;

    public BasicResponse() {
        code=SUCCESSCODE;
    }
}
