package com.sc.tradmaster.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.sc.tradmaster.utils.SysContent;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author grl 2017-1-04
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
    	WebUtils.issueRedirect(request, response, getSuccessUrl());
    }
}
