package com.neplus.erp.service;

import com.neplus.erp.bean.clientmanager.ClientManagerBO;
import com.neplus.erp.bean.clientmanager.ClientManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface TaskManagerService extends BaseService
{
	/**
	 * Paginated query of the tasks list
	 * @param condition
	 * @param roleType
	 * @param limit
	 * @param curPage
	 * @return
	 * @throws ServiceException
	 */
	PageResult<TaskManagerBO> taskManagerPageQuery(TaskManagerDTO condition, Integer roleType, int limit, int curPage) throws ServiceException;

	/**
	 * Fetch the particular task information base on the task id
	 * @param taskId
	 * @return
	 * @throws ServiceException
	 */
	TaskManagerBO getTaskInfo(Integer taskId) throws ServiceException;

	/**
	 * Delete the particular task by id
	 * @param taskId
	 * @return
	 * @throws ServiceException
	 */
	Boolean deleteTaskById(Integer taskId) throws ServiceException;

	/**
	 *  Upload the document for doc, banknotes or draft depends on attachmentType
	 * @param attachmentType
	 * @param base64file
	 * @param filename
	 * @return
	 * @throws ServiceException
	 */
	String uploadAttachment(Integer taskId, Integer attachmentType, String base64file, String filename) throws ServiceException;
}
