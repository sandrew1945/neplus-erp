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

    private static final String ATTACHMENT_PATH = "/attachment";

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
    public String uploadAttachment(Integer taskId, Integer attachmentType, String base64file, String filename) throws ServiceException
    {
        try
        {
            TtTaskPO taskInDB = ttTaskPOMapper.selectByPrimaryKey(taskId);
            Integer taskStatus = taskInDB.getTaskStatus();
            // firstly, upload the file and insert the record in the tm_file.
            TmFilePO filePO = commonService.insertFile(ATTACHMENT_PATH, filename, base64file);
            // then update the corresponding field in tm_task.
            // when attachmentType is 1, update the field of doc_file with file id
            // when attachmentType is 2, update the field of bank_notes_file with file id
            // when attachmentType is 3, update the field of draft_file with file id
            TtTaskPO condition = new TtTaskPO();
            condition.setTaskId(taskId);
            condition.setUpdateBy(getLoginUser().getUserCode());
            condition.setUpdateDate(new Date());
            switch (attachmentType)
            {
                case 1:
//                    condition.setDocFile(filePO.getFileId());
//                    condition.setDocArchiveDate(new Date());
                    // Check current task status, if current status is greater than Fixcode.TASK_STATUS_DOC_ARCHIVE, don't update the status
//                    if (taskStatus < Fixcode.TASK_STATUS_DOC_ARCHIVE.fixcode)
//                    {
//                        condition.setTaskStatus(Fixcode.TASK_STATUS_DOC_ARCHIVE.fixcode);
//                    }
                    break;
                case 2:
//                    condition.setBankNotesFile(filePO.getFileId());
//                    condition.setBankNotesArchiveDate(new Date());
                    // Check current task status, if current status is greater than Fixcode.TASK_STATUS_INVOICE_ARCHIVE, don't update the status
//                    if (taskStatus < Fixcode.TASK_STATUS_INVOICE_ARCHIVE.fixcode)
//                    {
//                        condition.setTaskStatus(Fixcode.TASK_STATUS_INVOICE_ARCHIVE.fixcode);
//                    }
                    break;
                case 3:
//                    condition.setDraftFile(filePO.getFileId());
//                    condition.setDocArchiveDate(new Date());
                    // Check current task status, if current status is greater than Fixcode.TASK_STATUS_DRAFT, don't update the status
                    if (taskStatus < Fixcode.TASK_STATUS_DRAFT.fixcode)
                    {
                        condition.setTaskStatus(Fixcode.TASK_STATUS_DRAFT.fixcode);
                    }
                    break;
                default:
            }
            ttTaskPOMapper.updateByPrimaryKeySelective(condition);
            return filePO.getFilePath();
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to upload the attachment.", e);
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
    public Boolean updateTaskStatus(Integer taskId, Integer toStatus, String comment, MultipartFile file) throws ServiceException
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
            if (null != file)
            {
                TmFilePO filePO = commonService.insertFile(ATTACHMENT_PATH, file);
                Integer fileId = filePO.getFileId();
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
            int count = ttTaskPOMapper.updateByPrimaryKey(condition);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status.", e);
        }
    }

    @Override
    public Boolean updateTaskToStart(Integer taskId, String comment, MultipartFile file) throws ServiceException
    {
        try
        {
            return updateTaskStatus(taskId, Fixcode.TASK_STATUS_PROCESSING.fixcode, comment, file);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_PROCESSING.getDesc(), e);
        }
    }
}
