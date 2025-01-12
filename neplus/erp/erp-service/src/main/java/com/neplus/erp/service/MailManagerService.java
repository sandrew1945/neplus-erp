package com.neplus.erp.service;

import com.neplus.erp.bean.mailmanager.MailManagerBO;
import com.neplus.erp.bean.mailmanager.MailManagerDTO;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;

import java.util.List;


/**
 * Function    : Mail template Management Service
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface MailManagerService extends BaseService
{
	/**
	 *  Paginated query the mail template list.
	 * @param condition
	 * @param limit
	 * @param curPage
	 * @return
	 * @throws ServiceException
	 */
	PageResult<MailManagerBO> mailManagerPageQuery(MailManagerDTO condition, int limit, int curPage) throws ServiceException;

	/**
	 *  Fetch the particular mail template information base on the template id
	 * @param templateId
	 * @return
	 * @throws ServiceException
	 */
	MailManagerBO getMailTemplate(Integer templateId) throws ServiceException;

	/**
	 *  Create a mail template.
	 * @param mailManagerDTO
	 * @return
	 * @throws ServiceException
	 */
	Boolean createMailTemplate(MailManagerDTO mailManagerDTO) throws ServiceException;

	/**
	 *  Delete the particular mail template by id
	 * @param templateId
	 * @return
	 * @throws ServiceException
	 */
	Boolean deleteMailTemplateById(Integer templateId) throws ServiceException;

	/**
	 *  Update the particular mail template information by template id
	 * @param mailManagerDTO
	 * @return
	 * @throws ServiceException
	 */
	Boolean updateMailTemplate(MailManagerDTO mailManagerDTO) throws ServiceException;

	/**
	 *  Fetch all the valid mail template
	 * @return
	 * @throws ServiceException
	 */
	List<MailManagerBO> getAllMailTemplate() throws ServiceException;

}
