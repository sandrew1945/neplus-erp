package com.neplus.erp.controller;


import com.neplus.framework.core.bean.AclUserBean;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.apache.ibatis.ognl.Ognl.setValue;


@Slf4j
public class BaseController
{
    protected final static String LOGIN_USER = "loginUser";
    protected final static String APP_KEY = "authc.appKey";

    @Resource
    protected HttpServletRequest request;

    @InitBinder
    protected void ininBinder(WebDataBinder binder)
    {
        //        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text) throws IllegalArgumentException
            {
                if (StringUtils.isNotEmpty(text))
                {
                    setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                }
            }
        });
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text) throws IllegalArgumentException
            {
                if (StringUtils.isNotEmpty(text))
                {
                    setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                }
            }
        });
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text) throws IllegalArgumentException
            {
                if (StringUtils.isNotEmpty(text))
                {
                    setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss")));
                }
            }
        });
    }

    /**
     * Function    : 获取登录用户信息
     * LastUpdate  : 2014年5月20日
     *
     * @return
     */
    protected AclUserBean getLoginUser()
    {
        log.debug("user token ========> " + request.getHeader("sid"));
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        AclUserBean loginUser = (AclUserBean) session.getAttribute(LOGIN_USER);
        return loginUser;
    }

    /**
     * Function    : 设置用户登录信息
     * LastUpdate  : 2014年5月20日
     *
     * @param loginUser
     */
    protected void setloginUser(AclUserBean loginUser)
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(LOGIN_USER, loginUser);
    }

    /**
     * 获取系统的appKey
     *
     * @return
     */
    protected String getAppKey()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (String) session.getAttribute(APP_KEY);
    }

}