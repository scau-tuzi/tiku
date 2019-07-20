package io.swagger.utils;

import io.swagger.pojo.dao.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("role:create");
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号.

        String username = (String)token.getPrincipal();

        System.out.println(token.getCredentials());

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = new User();
        user.setUsername("czfshine");
        user.setPasswordSaltMd5("123456");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                new SimplePrincipalCollection(user, getName()), //用户名
                user.getPasswordSaltMd5() //密码
        );
        return authenticationInfo;
    }
}