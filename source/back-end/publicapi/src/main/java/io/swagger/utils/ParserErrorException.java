package io.swagger.utils;

/**
 * 查询表达式执行异常
 */
public class ParserErrorException extends Exception {
    private String message;

    public ParserErrorException(String message){
        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
