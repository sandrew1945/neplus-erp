package com.neplus.erp.service.impl;


import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskProcessBO;
import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TtTaskPOMapper;
import com.neplus.erp.mapper.TtTaskProcessPOMapper;
import com.neplus.erp.mapper.custom.TaskManagerMapper;
import com.neplus.erp.model.TmFilePO;
import com.neplus.erp.model.TtTaskPO;
import com.neplus.erp.model.TtTaskProcessPO;
import com.neplus.erp.service.CommonService;
import com.neplus.erp.service.TaskManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.mybatis.PageQueryBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Boolean updateTaskStatus(Integer taskId, Integer toStatus, String comment, Integer fileId) throws ServiceException
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
            int count = ttTaskPOMapper.updateByPrimaryKeySelective(condition);
            return count > 0;
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
            return updateTaskStatus(taskId, Fixcode.TASK_STATUS_PROCESSING.fixcode, comment, fileId);
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
            return updateTaskStatus(taskId, Fixcode.TASK_STATUS_SELF_EXAM.fixcode, comment, fileId);
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
            return updateTaskStatus(taskId, Fixcode.TASK_STATUS_APPROVED.fixcode, comment, fileId);
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
            return updateTaskStatus(taskId, Fixcode.TASK_STATUS_INNER_REJ.fixcode, comment, fileId);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_INNER_REJ.getDesc(), e);
        }
    }
}
