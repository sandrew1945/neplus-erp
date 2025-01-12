package com.neplus.erp.controller;


import com.neplus.erp.bean.mailmanager.MailManagerBO;
import com.neplus.erp.bean.mailmanager.MailManagerConvertor;
import com.neplus.erp.bean.mailmanager.MailManagerDTO;
import com.neplus.erp.bean.mailmanager.MailVO;
import com.neplus.erp.bean.rolemanager.RoleManagerConvertor;
import com.neplus.erp.bean.usermanager.*;
import com.neplus.erp.service.MailManagerService;
import com.neplus.erp.service.UserManagerService;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.JsonException;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.result.JsonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mailmanager")
public class MailManagerController extends BaseController
{
    @Resource
    private MailManagerService mailManagerService;
    @Resource
    private MailManagerConvertor mailManagerConvertor;

    /**
     *  Paginated query the mail template list base on specific conditions.
     * @param templateName
     * @param templateStatus
     * @param limit
     * @param curPage
     * @return
     * @throws JsonException
     */
    @PostMapping(value = "/mailTemplatePageQuery")
    public JsonResult<PageResult<MailVO>> mailTemplatePageQuery(@RequestParam(required = false) String templateName, @RequestParam(required = false) Integer templateStatus, int limit, int curPage) throws JsonException
    {
        JsonResult<PageResult<MailVO>> result = new JsonResult<>();
        try
        {
            MailManagerDTO condition = new MailManagerDTO();
            condition.setTemplateName(templateName);
            condition.setTemplateStatus(templateStatus);
            // BO转VO
            PageResult<MailManagerBO> pageResult = mailManagerService.mailManagerPageQuery(condition, limit, curPage);

            PageResult<MailVO> pr = pageResult.convert(originPageResult -> {
                return mailManagerConvertor.toMailVO(originPageResult);
            });
            result.requestSuccess(pr);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  Create a new mail template
     * @param mail
     * @return
     * @throws JsonException
     */
    @PostMapping(value = "/createMailTemplate")
    public JsonResult<Boolean> createMailTemplate(MailManagerDTO mail) throws JsonException
    {
        JsonResult<Boolean> result = new JsonResult<>();
        try
        {
            result.requestSuccess(mailManagerService.createMailTemplate(mail));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  Fetch the mail template by id
     * @param templateId
     * @return
     * @throws JsonException
     */
    @GetMapping("getMailTemplateById")
    public JsonResult<MailVO> getMailTemplateById(Integer templateId) throws JsonException
    {
        JsonResult<MailVO> result = new JsonResult<>();
        try
        {
            MailManagerBO mailTemplate = mailManagerService.getMailTemplate(templateId);
            result.requestSuccess(mailManagerConvertor.toMailVO(mailTemplate));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("Failed to fetch the mail template.", e);
        }
        return result;
    }

    /**
     *  Update the mail template info by id
     * @param mail
     * @return
     * @throws JsonException
     */
    @PostMapping(value = "/updateMailTemplate")
    public JsonResult<Boolean> updateMailTemplate(MailManagerDTO mail) throws JsonException
    {
        JsonResult<Boolean> result = new JsonResult<>();
        try
        {
            result.requestSuccess(mailManagerService.updateMailTemplate(mail));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  Delete the specific mail template by id.
     * @param templateId
     * @return
     * @throws JsonException
     */
    @PostMapping(value = "/deleteMailTemplate")
    public JsonResult<Boolean> deleteMailTemplate(Integer templateId) throws JsonException
    {
        JsonResult<Boolean> result = new JsonResult<>();
        try
        {
            result.requestSuccess(mailManagerService.deleteMailTemplateById(templateId));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }

    /**
     *  Fetch all the valid mail templates.
     * @return
     * @throws JsonException
     */
    @GetMapping(value = "getAllMailTemplate")
    public JsonResult<List<MailVO>> getAllMailTemplate() throws JsonException
    {
        JsonResult<List<MailVO>> result = new JsonResult<>();
        try
        {
            List<MailVO> mailVOList = mailManagerConvertor.toMailVO(mailManagerService.getAllMailTemplate());
            result.requestSuccess(mailVOList);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

}
