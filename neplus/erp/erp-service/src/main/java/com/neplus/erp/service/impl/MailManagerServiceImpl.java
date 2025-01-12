package com.neplus.erp.service.impl;

import com.neplus.erp.bean.mailmanager.MailManagerBO;
import com.neplus.erp.bean.mailmanager.MailManagerConvertor;
import com.neplus.erp.bean.mailmanager.MailManagerDTO;
import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TtMailTemplatePOMapper;
import com.neplus.erp.mapper.custom.MailManagerMapper;
import com.neplus.erp.model.TtMailTemplatePO;
import com.neplus.erp.model.TtMailTemplatePOExample;
import com.neplus.erp.service.MailManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.mybatis.PageQueryBuilder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailManagerServiceImpl implements MailManagerService
{
    @Resource
    private MailManagerMapper mailManagerMapper;
    @Resource
    private TtMailTemplatePOMapper ttMailTemplatePOMapper;
    @Resource
    private MailManagerConvertor mailManagerConvertor;

    @Override
    public PageResult<MailManagerBO> mailManagerPageQuery(MailManagerDTO condition, int limit, int curPage) throws ServiceException
    {
        try
        {
            return PageQueryBuilder.pageQuery(mailManagerMapper, "mailManagerPageQuery", condition, curPage, limit);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to query mail template list", e);
        }
    }

    @Override
    public MailManagerBO getMailTemplate(Integer templateId) throws ServiceException
    {
        try
        {
            TtMailTemplatePO mailTemplatePO = ttMailTemplatePOMapper.selectByPrimaryKey(templateId);
            return mailManagerConvertor.toMailManagerBO(mailTemplatePO);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to fetch mail template", e);
        }
    }

    @Override
    public Boolean createMailTemplate(MailManagerDTO mailManagerDTO) throws ServiceException
    {
        try
        {
            TtMailTemplatePO mailTemplatePO = mailManagerConvertor.toMailPO(mailManagerDTO);
            mailTemplatePO.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
            mailTemplatePO.setCreateBy(getLoginUser().getUserCode());
            mailTemplatePO.setCreateDate(new Date());
            int count = ttMailTemplatePOMapper.insertSelective(mailTemplatePO);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to create mail template", e);
        }
    }

    @Override
    public Boolean deleteMailTemplateById(Integer templateId) throws ServiceException
    {
        try
        {
            TtMailTemplatePO mailTemplatePO = new TtMailTemplatePO();
            mailTemplatePO.setTemplateId(templateId);
            mailTemplatePO.setIsDelete(Fixcode.IF_TYPE_YES.fixcode);
            mailTemplatePO.setUpdateBy(getLoginUser().getUserCode());
            mailTemplatePO.setUpdateDate(new Date());
            int count = ttMailTemplatePOMapper.updateByPrimaryKeySelective(mailTemplatePO);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to delete mail template", e);
        }
    }

    @Override
    public Boolean updateMailTemplate(MailManagerDTO mailManagerDTO) throws ServiceException
    {
        try
        {
            TtMailTemplatePO condition = mailManagerConvertor.toMailPO(mailManagerDTO);
            condition.setUpdateBy(getLoginUser().getUserCode());
            condition.setUpdateDate(new Date());
            int count = ttMailTemplatePOMapper.updateByPrimaryKeySelective(condition);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update mail template", e);
        }
    }

    @Override
    public List<MailManagerBO> getAllMailTemplate() throws ServiceException
    {
        try
        {
            TtMailTemplatePOExample conditions = new TtMailTemplatePOExample();
            TtMailTemplatePOExample.Criteria criteria = conditions.createCriteria();
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.fixcode);
            List<TtMailTemplatePO> mailTemplateList = ttMailTemplatePOMapper.selectByExample(conditions);
            return mailManagerConvertor.toMailManagerBO(mailTemplateList);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to fetch all mail template", e);
        }
    }
}
