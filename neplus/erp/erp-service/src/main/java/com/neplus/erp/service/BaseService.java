package com.neplus.erp.service;

import com.neplus.framework.core.bean.AclUserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import static com.neplus.erp.dictionary.Constants.LOGIN_USER;

public interface BaseService
{
    default AclUserBean getLoginUser()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        AclUserBean loginUser = (AclUserBean) session.getAttribute(LOGIN_USER);
        return loginUser;
    }
}
