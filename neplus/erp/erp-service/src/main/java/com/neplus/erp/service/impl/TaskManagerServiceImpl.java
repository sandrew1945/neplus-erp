package com.neplus.erp.service.impl;

import cn.nesc.mailsender.MailSender;
import cn.nesc.mailsender.exceptions.MailSenderException;
import cn.nesc.mailsender.mail.EmailType;
import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskProcessBO;
import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.*;
import com.neplus.erp.mapper.custom.TaskManagerMapper;
import com.neplus.erp.model.*;
import com.neplus.erp.service.TaskManagerService;
import com.neplus.framework.core.bean.PageResult;
import com.neplus.framework.core.exception.ServiceException;
import com.neplus.framework.core.mybatis.PageQueryBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
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
    private TtTaskProcessPOMapper ttTaskProcessPOMapper;
    @Resource
    private TtMailLogPOMapper ttMailLogPOMapper;
    @Resource
    private TmUserPOMapper tmUserPOMapper;
    @Resource
    private TmFilePOMapper tmFilePOMapper;
    @Value("${neplus.email.send.switch}")
    private boolean isSendEmail;
    @Value("${file.local.path}")
    private String basePath;
    @Value("${neplus.monthly.task.title}")
    private String monthlyTaskDeclarationTitle;
    @Value("${neplus.quarterly.task.title}")
    private String quarterlyTaskDeclarationTitle;


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
    public Boolean updateTaskToDraftSend(Integer taskId, String subject, String mailContent, Integer fileId) throws ServiceException
    {
        try
        {
            TaskManagerBO taskInfo = getTaskInfo(taskId);
            Integer userId = getLoginUser().getUserId();
            TmUserPO userPO = tmUserPOMapper.selectByPrimaryKey(userId);
            String clientEmail = taskInfo.getClientEmail();
            // Send the email
            if (isSendEmail)
            {
                log.debug("Sending Email ..... ");
                if (StringUtils.isNotEmpty(clientEmail))
                {
                    // split clientEmails by ';' if client has multiple emails.
                    String[] mailArray = StringUtils.split(clientEmail, ";");
                    MailSender sender = new MailSender();
                    sender.setMailServer("smtp.gmail.com");
                    if (StringUtils.isEmpty(userPO.getEmail()) || StringUtils.isEmpty(userPO.getEmailToken()))
                    {
                        throw new ServiceException("用户邮箱或Token未配置");
                    }
                    sender.setUsername(userPO.getEmail());
                    sender.setPassword(userPO.getEmailToken());
                    // Retrieve the PDF for send email.
                    String sysTempDir = System.getProperty("java.io.tmpdir");
                    log.debug("SysTempDir ---->" + sysTempDir);
                    for (String mailAddress : mailArray)
                    {
                        TmFilePO filePO = tmFilePOMapper.selectByPrimaryKey(fileId);
                        String relativePath = filePO.getFilePath();
                        if (basePath.endsWith("/"))
                        {
                            basePath = basePath.substring(0, basePath.length() - 1);
                        }
                        if (!relativePath.startsWith(File.separator))
                        {
                            relativePath = File.separator + relativePath;
                        }
                        // Copy the File to System temporary directive.
                        FileUtils.copyFile(new File(basePath + relativePath), new File(sysTempDir + File.separator + filePO.getFileName()));
                        sender.sendMailWithAttachment(mailAddress, subject, mailContent, EmailType.HTML, sysTempDir + File.separator + filePO.getFileName());
                    }

                }
            }
            // Update the task's status
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_DRAFT.fixcode, null, fileId);
            // Lastly, record the email sending log.
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
    public Boolean updateTaskToDeclaration(Integer taskId, String subject, String emailContent, Integer fileId) throws ServiceException
    {
        try
        {
            TaskManagerBO taskInfo = getTaskInfo(taskId);
            Integer userId = getLoginUser().getUserId();
            TmUserPO userPO = tmUserPOMapper.selectByPrimaryKey(userId);
            String clientEmail = taskInfo.getClientEmail();
            // Send the email
            if (isSendEmail)
            {
                log.debug("Sending Email ..... ");
                if (StringUtils.isNotEmpty(clientEmail))
                {
                    // split clientEmails by ';' if client has multiple emails.
                    String[] mailArray = StringUtils.split(clientEmail, ";");
                    MailSender sender = new MailSender();
                    sender.setMailServer("smtp.gmail.com");
                    if (StringUtils.isEmpty(userPO.getEmail()) || StringUtils.isEmpty(userPO.getEmailToken()))
                    {
                        throw new ServiceException("用户邮箱或Token未配置");
                    }
                    sender.setUsername(userPO.getEmail());
                    sender.setPassword(userPO.getEmailToken());
                    // Retrieve the PDF for send email.
                    String sysTempDir = System.getProperty("java.io.tmpdir");
                    log.debug("SysTempDir ---->" + sysTempDir);
                    for (String mailAddress : mailArray)
                    {
                        TmFilePO filePO = tmFilePOMapper.selectByPrimaryKey(fileId);
                        String relativePath = filePO.getFilePath();
                        if (basePath.endsWith("/"))
                        {
                            basePath = basePath.substring(0, basePath.length() - 1);
                        }
                        if (!relativePath.startsWith(File.separator))
                        {
                            relativePath = File.separator + relativePath;
                        }
                        // Copy the File to System temporary directive.
                        FileUtils.copyFile(new File(basePath + relativePath), new File(sysTempDir + File.separator + filePO.getFileName()));
                        sender.sendMailWithAttachment(mailAddress, subject, emailContent, EmailType.HTML, sysTempDir + File.separator + filePO.getFileName());
                    }

                }
            }
            // Update the task's status
            Integer processId = updateTaskStatus(taskId, Fixcode.TASK_STATUS_SUBMIT.fixcode, null, fileId);
            // Lastly, record the email sending log.
            TtMailLogPO mailLog = new TtMailLogPO();
            mailLog.setProcessId(processId);
            mailLog.setSenderId(getLoginUser().getUserId());
            mailLog.setRecipientEmail(clientEmail);
            mailLog.setAttachment(fileId);
            mailLog.setEmailContent(emailContent);
            mailLog.setSendTime(new Date());
            mailLog.setCreateBy(getLoginUser().getUserCode());
            mailLog.setCreateDate(new Date());
            int count = ttMailLogPOMapper.insertSelective(mailLog);
            return count > 0;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to update the task status to " + Fixcode.TASK_STATUS_SUBMIT.getDesc(), e);
        }
    }

    @Override
    public List<TmClientPO> getNoTaskClientByDate(String date) throws ServiceException
    {
        try
        {
            return taskManagerMapper.getNoTaskClientByDate(date);
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to retrieve the client which didn't create task in " + date, e);
        }
    }

    @Override
    public Boolean createTaskByDetermination(List<TmClientPO> clients, String yearAndMonth, String quarterOfYear) throws ServiceException
    {
        try
        {
            if (null != clients || clients.size() == 0)
            {
                log.debug("There is no client need to generate task.");
            }
            clients.stream().forEach(client -> {
                // Judge the type of task by client ICT and VAT
                Fixcode taskType = getTaskTypeByClientConditions(client);
                if (taskType.equals(Fixcode.DEC_TYPE_N))
                {
                    return;
                }
                TtTaskPO task = new TtTaskPO();
                task.setClientId(client.getClientId());
                if (taskType.equals(Fixcode.DEC_TYPE_M))
                {
                    task.setTaskName(String.format(monthlyTaskDeclarationTitle, yearAndMonth, client.getClientName()));
                    task.setTaskType(Fixcode.TASK_TYPE_M.fixcode);
                }
                else if (taskType.equals(Fixcode.DEC_TYPE_Q))
                {
                    // If the client need quarter task then check whether there is task for that quarter.
                    TtTaskPO taskByYearAndQuarter = taskManagerMapper.getTaskByYearAndQuarter(client.getClientId(), quarterOfYear, yearAndMonth.substring(3));
                    if (null != taskByYearAndQuarter)
                    {
                        return;
                    }
                    task.setTaskName(String.format(quarterlyTaskDeclarationTitle, quarterOfYear, client.getClientName()));
                    task.setTaskType(Fixcode.TASK_TYPE_Q.fixcode);
                }
                task.setTaskStatus(Fixcode.TASK_STATUS_UNPROCESSED.fixcode);
                task.setIsDelete(Fixcode.IF_TYPE_NO.fixcode);
                task.setCreateBy("JOB-CreateDeclarationTask");
                task.setCreateDate(new Date());
                ttTaskPOMapper.insertSelective(task);
            });
            return null;
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to create tasks for clients", e);
        }
    }

    private Fixcode getTaskTypeByClientConditions(TmClientPO client) throws ServiceException
    {
        try
        {
            String clientICT = client.getClientIct();
            String clientVAT = client.getClientVat();
            if (Fixcode.DEC_TYPE_M.fixcode.toString().equals(clientICT) || Fixcode.DEC_TYPE_M.fixcode.toString().equals(clientVAT))
            {
                return Fixcode.DEC_TYPE_M;
            }
            else if (Fixcode.DEC_TYPE_Q.fixcode.toString().equals(clientICT) && Fixcode.DEC_TYPE_Q.fixcode.toString().equals(clientVAT))
            {
                return Fixcode.DEC_TYPE_Q;
            }
            else if (Fixcode.DEC_TYPE_N.fixcode.toString().equals(clientICT) && Fixcode.DEC_TYPE_N.fixcode.toString().equals(clientVAT))
            {
                return Fixcode.DEC_TYPE_N;
            }
            else if (Fixcode.DEC_TYPE_Q.fixcode.toString().equals(clientICT) || Fixcode.DEC_TYPE_Q.fixcode.toString().equals(clientVAT))
            {
                return Fixcode.DEC_TYPE_Q;
            }
            else
            {
                throw new ServiceException("Client's ICT or VAT format is incorrect, The client's ICT is:" + client.getClientIct() + " and client's VAT is:" + client.getClientVat());
            }
        }
        catch (Exception e)
        {
            throw new ServiceException("Failed to get the task type, The client's ICT is:" + client.getClientIct() + " and client's VAT is" + client.getClientVat(), e);
        }
    }
}
