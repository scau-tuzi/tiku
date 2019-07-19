package io.swagger.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重写过滤器，当没有权限不是用302跳到登录页面，而是返回401
 * 因为302直接被浏览器处理了，前端代码只能拿到302跳后的200
 */
public class TikuAuthcFilter extends FormAuthenticationFilter {
    public TikuAuthcFilter() {
        super();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return super.onAccessDenied(request, response);
        } else {
            if (isAjax((HttpServletRequest) request)) {
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.addHeader("REQUIRE_AUTH", "true");
                // 返回401
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

    /**
     * 判断是不是用代码请求api的情况
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return  accept!= null && accept.contains("application/json");
    }
}
