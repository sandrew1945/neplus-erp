package com.neplus.erp.bean.taskmanager;

import com.neplus.framework.core.bean.BO;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class TaskManagerBO implements BO
{
    private Integer taskId;
    private Integer clientId;
    private String clientName;
    private Integer clientType;
    private String clientEmail;
    private String clientMobile;
    private String optName;
    private Integer approveId;
    private String approveName;
    private String taskName;
    private Integer taskType;
    private Integer taskStatus;
    private Integer decDraftFile;
    private Date decDraftSendDate;
    private Integer decFormFile;
    private Date decFormSendDate;
    private String createBy;
    private Date createDate;

}
