package io.swagger.config;

import io.swagger.utils.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j

public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        log.info("ShiroConfiguration.shirFilter()");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();

        filters.put("authc", new TikuAuthcFilter()); // 自定义的过滤器
        filters.put("anon", new AnonymousFilter());
        filters.put("logout", new LogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //拦截器.

        HashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/web/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/api/login","anon");


        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/web/login");

        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/web/");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/web/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

//    /**
//     * 凭证匹配器
//     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//     * ）
//     * @return
//     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//        return hashedCredentialsMatcher;
//    }


    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        //userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }
//    /**
//     *  开启shiro aop注解支持.
//     *  使用代理方式;所以需要开启代码支持;
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

//    @Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver
//    createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
//        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
//        mappings.setProperty("UnauthorizedException","403");
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("ex");     // Default is "exception"
//        //r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
//    }
}