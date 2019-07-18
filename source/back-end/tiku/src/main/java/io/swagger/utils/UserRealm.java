package io.swagger.utils;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dao.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
    //private UserService userService = new UserServiceImpl();

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String) principals.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        // 根据用户名查询当前用户拥有的角色
//        Set<Role> roles = userService.findRoles(username);
//        Set<String> roleNames = new HashSet<String>();
//        for (Role role : roles) {
//            roleNames.add(role.getRole());
//        }
//        // 将角色名称提供给info
//        authorizationInfo.setRoles(roleNames);
//        // 根据用户名查询当前用户权限
//        Set<Permission> permissions = userService.findPermissions(username);
//        Set<String> permissionNames = new HashSet<String>();
//        for (Permission permission : permissions) {
//            permissionNames.add(permission.getPermission());
//        }
//        // 将权限名称提供给info
//        authorizationInfo.setStringPermissions(permissionNames);

        return null;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            // 用户名不存在抛出异常
//            throw new UnknownAccountException();
//        }
//        if (user.getLocked() == 0) {
//            // 用户被管理员锁定抛出异常
//            throw new LockedAccountException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
//                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
//        return authenticationInfo;
        return null;
    }
}