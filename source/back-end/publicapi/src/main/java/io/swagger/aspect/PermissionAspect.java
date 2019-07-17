package io.swagger.aspect;

import io.swagger.pojo.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
@Slf4j
@Order(10000)
public class PermissionAspect {
    @Pointcut("execution(public * io.swagger.api..*.*(..))")
    public void permission(){}

    /**
     * 权限参数异常
     */
    public class PermissionArgumentException extends Exception{
        public PermissionArgumentException(String message) {
            super(message);
        }
    }

    /**
     * 没有权限
     */
    public class NotPermissionException extends Exception{
        public NotPermissionException(String message) {
            super(message);
        }
    }


    @Around("permission()")
    public Object checkPermission(ProceedingJoinPoint jp) throws Throwable {
        //log.debug("开始验证权限");
        Object res;

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setCode("error");
        basicResponse.setData("服务器异常");

        try {

            // 拿token

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String method = request.getMethod().toUpperCase();
            String token=null;
            switch (method){
                //下面这些有请求体的
                case "POST":
                case "PUT":{
                    Object[] args = jp.getArgs();
                    if(args.length!=1){
                        log.error("POST等控制器方法有且只能有一个参数");
                        throw  new Exception(); //直接抛，然后会返回
                    }
                    Object arg1 = args[0];
                    Method getToken = arg1.getClass().getMethod("getToken");
                    if(getToken==null){
                        log.error("POST等控制器方法参数必须有getToken方法");
                        throw  new Exception(); //直接抛，然后会返回
                    }
                    Object tokenObj = getToken.invoke(arg1);
                    if(tokenObj==null){
                        //应该不会到这一步的，因为在swagger定义api时的token已经是必选的了
                        throw new PermissionArgumentException("请求体json的根元素必须有token属性");
                    }

                    if(!(tokenObj instanceof UUID)){
                        //应该不会到这一步的，因为在swagger定义api时的token已经是定义为字符串了
                        throw new PermissionArgumentException("请求体json的根元素的token属性必须是uuid字符串类型");
                    }
                    //成功拿到
                    token=  tokenObj.toString();
                    break;
                }
                //不能有请求体，从header拿token
                case "DELETE":{
                    String header = request.getHeader("X-tiku-token");
                    if(header==null){
                        //应该不会到这一步的，因为在swagger定义api时的token已经是必选的了
                        throw new PermissionArgumentException("DELETE请求必须在头部设置X-tiku-token为授权码");
                    }
                    token=header;
                    break;
                }
                default:
                {
                    throw new PermissionArgumentException("不支持的请求方法");
                }
            }

            //到这里就拿到token了
            //做校验
            //todo 从别的地方拿
            String t="06e599f3-78db-4c71-b4fa-2b496beab1f6";

            if(!t.equals(token)){
                throw new NotPermissionException("没有权限");
            }
        } catch (NotPermissionException e){
            basicResponse.setData(e.getMessage());
            return new ResponseEntity<>(basicResponse, HttpStatus.UNAUTHORIZED);
        } catch (PermissionArgumentException e){
            basicResponse.setData(e.getMessage());
            return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            // 出现异常，这里直接返回，防止控制器被调用
            return new ResponseEntity<>(basicResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {

            res = jp.proceed();
        }catch (Exception e){
            //捕获到未处理的异常

            res= new ResponseEntity<>(basicResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return res;
    }

}
