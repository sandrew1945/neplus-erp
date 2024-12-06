package com.neplus.erp.bean.taskmanager;

import lombok.Data;

import java.util.Date;

@Data
public class TaskVO
{
    private Integer taskId;
    private Integer clientId;
    private String clientName;
    private Integer clientType;
    private String clientEmail;
    private String clientMobile;
    private String clientTel;
    private String optName;
    private String approveName;
    private Integer taskType;
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
