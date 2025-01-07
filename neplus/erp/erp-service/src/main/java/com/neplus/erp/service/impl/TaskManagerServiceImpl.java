package com.neplus.erp.service.impl;


import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskProcessBO;
import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TtMailLogPOMapper;
import com.neplus.erp.mapper.TtTaskPOMapper;
import com.neplus.erp.mapper.TtTaskProcessPOMapper;
import com.neplus.erp.mapper.custom.TaskManagerMapper;
import com.neplus.erp.model.TtMailLogPO;
import com.neplus.erp.model.TtTaskPO;
import com.neplus.erp.model.TtTaskProcessPO;
import com.neplus.erp.service.CommonService;
import com.neplus.erp.service.TaskManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.mybatis.PageQueryBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 *  Task Management Service
 *
 */
@Service
@Slf4j
public class TaskManagerServiceImpl implements TaskManagerService
{

    @Resource
    private TaskManagerMapper taskManagerMapper;
    @Resource
    private TtTaskPOMapper ttTaskPOMapper;
    @Resource
    private CommonService commonService;
    @Resource
    private TtTaskProcessPOMapper ttTaskProcessPOMapper;
    @Resource
    private TtMailLogPOMapper ttMailLogPOMapper;
    @Value("${neplus.email.send.switch}")
    private boolean isSendEmail;


    @Override
    public PageResult<TaskManagerBO> taskManagerPageQuery(TaskManagerDTO condition, Integer roleType, int limit, int curPage) throws ServiceException
    {
        try
        {
            // judge the role, if the role type is staff, only show the client they are responsible for.
            if (Fixcode.ROLE_TYPE_STAFF.fixcode.equals(roleType))
            {
                condition.setOptId(getLoginUser().getUserId());
            }
//            if (Fixcode.ROLE_TYPE_ADMIN.fixcode.equals(roleType) || Fixcode.ROLE_TYPE_SYSTEM.fixcode.equals(roleType))
//            {
//                condition.setApproveId(getLoginUser().getUserId());
//            }
            return PageQueryBuilder.pageQuery(taskManagerMapper, "taskManagerPageQuery", condition, curPage, limit);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to query the tasks list.", e);
        }
    }

    @Override
    public TaskManagerBO getTaskInfo(Integer taskId) throws ServiceException
    {
        try
        {
            return taskManagerMapper.getTaskInfoById(taskId);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to query the task information.", e);
        }
    }

    @Override
    public Boolean deleteTaskById(Integer taskId) throws ServiceException
    {
        try
        {
            TtTaskPO condition = new TtTaskPO();
            condition.setTaskId(taskId);
            condition.setIsDelete(Fixcode.IF_TYPE_YES.fixcode);
            condition.setUpdateBy(getLoginUser().getUserCode());
            condition.setUpdateDate(new Date());
            int count = ttTaskPOMapper.updateByPrimaryKeySelective(condition);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to delete the task[" + taskId + "].", e);
        }
    }

    @Override
    public List<TaskProcessBO> getTaskProcessList(Integer taskId) throws ServiceException
    {
        try
        {
            return taskManagerMapper.getTaskProcessList(taskId);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to query the task processes list.", e);
        }
    }

    @Override
    public Integer updateTaskStatus(Integer taskId, Integer toStatus, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            // Firstly, create a record of task process.
            TtTaskPO originTask = ttTaskPOMapper.selectByPrimaryKey(taskId);
            TtTaskProcessPO taskProcess = new TtTaskProcessPO();
            taskProcess.setTaskId(taskId);
            taskProcess.setOptDate(new Date());
            taskProcess.setOptBy("" + getLoginUser().getUserId());
            taskProcess.setTaskStatusFrom(originTask.getTaskStatus());
            taskProcess.setTaskStatusTo(toStatus);
            taskProcess.setComment(comment);
            if (null != fileId)
            {
                taskProcess.setFileId(fileId);
            }
            taskProcess.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
            taskProcess.setCreateBy(getLoginUser().getUserCode());
            taskProcess.setCreateDate(new Date());
            ttTaskProcessPOMapper.insertSelective(taskProcess);
            // Then update the task's status
            TtTaskPO condition = new TtTaskPO();
            condition.setTaskId(taskId);
            condition.setTaskStatus(toStatus);
            condition.setUpdateBy(getLoginUser().getUserCode());
            condition.setUpdateDate(new Date());
            ttTaskPOMapper.updateByPrimaryKeySelective(condition);
            return taskProcess.getProcessId();
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status.", e);
        }
    }

    @Override
    public Boolean updateTaskToStart(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId= updateTaskStatus(taskId, Fixcode.TASK_STATUS_PROCESSING.fixcode, comment, fileId);
            return null != processId;
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_PROCESSING.getDesc(), e);
        }
    }

    public Boolean updateTaskToSelfApproved(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_SELF_EXAM.fixcode, comment, fileId);
            return null != processId;
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_SELF_EXAM.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToApproved(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_APPROVED.fixcode, comment, fileId);
            return null != processId;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_APPROVED.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToInnerReject(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_INNER_REJ.fixcode, comment, fileId);
            return null != processId;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_INNER_REJ.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToDraftSend(Integer taskId, String mailContent, Integer fileId) throws ServiceException
    {
        try
        {
            // Send the email
            if (isSendEmail)
            {
                log.debug("Sending Email ..... ");
            }
            // Update the task's status
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_DRAFT.fixcode, null, fileId);
            // Lastly, record the email sending log.
            String clientEmail = getTaskInfo(taskId).getClientEmail();
            TtMailLogPO mailLog = new TtMailLogPO();
            mailLog.setProcessId(processId);
            mailLog.setSenderId(getLoginUser().getUserId());
            mailLog.setRecipientEmail(clientEmail);
            mailLog.setAttachment(fileId);
            mailLog.setEmailContent(mailContent);
            mailLog.setSendTime(new Date());
            mailLog.setCreateBy(getLoginUser().getUserCode());
            mailLog.setCreateDate(new Date());
            int count = ttMailLogPOMapper.insertSelective(mailLog);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_DRAFT.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToClientApproved(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_UNDECLARATION.fixcode, comment, fileId);
            return null != processId;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_UNDECLARATION.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToClientReject(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_OUTER_REJ.fixcode, comment, fileId);
            return null != processId;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_OUTER_REJ.getDesc(), e);
        }
    }

    @Override
    public Boolean updateTaskToDeclaration(Integer taskId, String comment, Integer fileId) throws ServiceException
    {
        try
        {
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_SUBMIT.fixcode, comment, fileId);
            return null != processId;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_SUBMIT.getDesc(), e);
        }
    }
}
