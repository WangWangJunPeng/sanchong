package com.sc.tradmaster.shiro;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.sc.tradmaster.bean.Role;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.dao.BaseDao;
import com.sc.tradmaster.dao.impl.BaseDaoImpl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

/**
 * 
 * @author grl 2017-1-04
 *
 */

public class MyRealm extends AuthorizingRealm {
    private static Log LOG = LogFactory.getLog(MyRealm.class);
    public static String LOGIN_KEY = "login_user";

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        Set<String> roleList = new HashSet<String>();
        Set<String> permissionList = new HashSet<String>();
        //从数据库中获取当前登录用户的详细信息
        BaseDao baseDao = new BaseDaoImpl();
        String hql = "from User phone = " + currentUsername;
        User user = (User) baseDao.findByHql(hql);
        LOG.info("验证用户权限");
        if (null != user) {
            if (CollectionUtils.isEmpty(user.getRoleId())) {
                throw new AuthorizationException();
            }
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            for(Role role:user.getRoleId()){
                simpleAuthorInfo.addRole(role.getRoleName());
                simpleAuthorInfo.addStringPermission(role+":*");
            }
            simpleAuthorInfo.addRoles(roleList);
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        } else {
            throw new AuthorizationException();
        }
    }


    /**
     * 验证当前登录的用户
     *
     * @see:该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
	@Override					 
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的
    	
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LOG.info("------验证当前Subject时获取到token为------" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
        BaseDao baseDao = new BaseDaoImpl();
        String hql = "from User phone = " + token.getUsername();
        User user = (User) baseDao.findByHql(hql);
        if (user != null) {
            if (CollectionUtils.isEmpty(user.getRoleId())) {
                throw new AuthenticationException();
            }
            this.setSession(LOGIN_KEY, LoginUserInfo.fromPerson(user));
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(), this.getName());
            return authenticationInfo;
        } else {
            throw new AuthenticationException();
        }
    }

    /**
     * 清空Session，退出登录时使用
     */
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        Session session = SecurityUtils.getSubject().getSession();
        for (Object key : session.getAttributeKeys()) {
            session.removeAttribute(key);
        }
    }
    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * @see:比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */

    private static Session findSession() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            LOG.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            return session;
        }
        return null;
    }

    public static void setSession(Object key, Object value) {
        Session session = findSession();
        LOG.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
        if (null != session) {
            session.setAttribute(key, value);
        }
    }


    public static <T> T getSession(Object key) {
        Session session = findSession();
        LOG.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
        if (null != session) {
            return (T) session.getAttribute(key);
        }
        return null;
    }
}
