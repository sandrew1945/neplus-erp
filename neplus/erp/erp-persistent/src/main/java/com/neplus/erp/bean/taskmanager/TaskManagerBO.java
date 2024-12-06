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
    private String clientTel;
    private Integer taskType;
    private String optName;
    private String approveName;
    private Integer taskStatus;
    private Integer docFile;
    private Date docArchiveDate;
    private String docFilePath;
    private Integer bankNotesFile;
    private Date bankNotesArchiveDate;
    private String bankNotesFilePath;
    private Integer selfExamFile;
    private Date selfExamArchiveDate;
    private Integer draftFile;
    private Date draftCreateDate;
    private String draftFilePath;
    private Date dirRejDate;
    private String dirRejCmnt;
    private Date cliRejDate;
    private String cliRejCmnt;
    private Integer isFetchDoc;
    private String createDate;
}
