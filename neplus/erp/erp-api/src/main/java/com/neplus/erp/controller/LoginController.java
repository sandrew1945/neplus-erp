package com.neplus.erp.controller;


import com.neplus.erp.bean.RoleBean;
import com.neplus.erp.bean.login.LoginConvertor;
import com.neplus.erp.bean.login.LoginVO;
import com.neplus.erp.bean.rolemanager.RoleManagerConvertor;
import com.neplus.erp.bean.usermanager.UserInfoVO;
import com.neplus.erp.bean.usermanager.UserManagerBO;
import com.neplus.erp.model.TmRolePO;
import com.neplus.erp.model.TmRoleVO;
import com.neplus.erp.service.LoginService;
import com.neplus.erp.service.RoleManagerService;
import com.neplus.erp.service.UserManagerService;
import com.neplus.erp.service.util.MenuNode;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.exception.JsonException;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.result.JsonResult;
import com.neplus.framework.core.shiro.MyUsernamePasswordToken;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.PrimitiveIterator;


@RestController
@Slf4j
public class LoginController extends BaseController
{
    @Resource
    private LoginService loginService;
    @Resource
    private LoginConvertor loginConvertor;
    @Resource
    private UserManagerService userManagerService;
    @Resource
    private RoleManagerConvertor roleManagerConvertor;
    @Resource
    private RoleManagerService roleManagerService;

    @PostMapping(value = "/login")
    public JsonResult login(String userCode, String password) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            MyUsernamePasswordToken token = new MyUsernamePasswordToken();
            token.setUsername(userCode);
            token.setPassword(password.toCharArray());
            result = result.requestSuccess(loginService.login(token));
        }
        catch (Exception e)
        {
            String errorMsg = null;
            if (e instanceof ServiceException)
            {
                errorMsg = "用户名或密码错误";
            }
            else
            {
                errorMsg = "用户登录失败";
            }
            result.requestFailure(errorMsg);
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @GetMapping(value = "/userInfo")
    public JsonResult userInfo() throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            UserInfoVO userInfo = new UserInfoVO();
            UserManagerBO user = userManagerService.findByUserId(getLoginUser().getUserId());
            userInfo = loginConvertor.toUserInfoVO(user);
            List<TmRoleVO> roleList = roleManagerConvertor.toRoleVOList(userManagerService.getRelationRolesByUserId(getLoginUser().getUserId()));
            userInfo.setRoleList(roleList);
            result = result.requestSuccess(userInfo);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
        return result;
    }

    @PostMapping(value = "/setCurrentlyRole")
    public JsonResult<RoleBean> setCurrentlyRole(Integer roleId)
    {
        JsonResult<RoleBean> result = new JsonResult<>();
        try
        {
            return result.requestSuccess(loginService.setCurrentlyRole(roleId));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("Failed to set user role information", e);
        }
    }

    @GetMapping(value = "/validateToken")
    public JsonResult validateToken(String token) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            log.debug("token -------->" + token);
            result = result.requestSuccess(loginService.validateSession(token));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("验证登录状态失败", e);
        }
        return result;
    }

    @GetMapping(value = "/getMenuByRole")
    public JsonResult getMenuByRole(Integer roleId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            result = result.requestSuccess(loginService.getMenuByRole(roleId));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取系统菜单失败", e);
        }
        return result;
    }

    @PostMapping(value = "/logout")
    public JsonResult logout() throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            loginService.logout();
            result = result.requestSuccess(true);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("登出系统失败", e);
        }
        return result;
    }

}
