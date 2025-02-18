package com.neplus.erp.bean.taskmanager;

import lombok.Data;

import java.util.Date;

@Data
public class TaskManagerDTO
{
    private Integer taskId;
    private String clientName;
    private Integer clientType;
    private Integer taskType;
    private Integer taskStatus;
    private Date docArchiveDate;
    private Date bankNotesArchiveDate;
    private Date selfExamArchiveDate;
    private String startFrom;
    private String endWith;
    private Date draftCreateDate;
    private String dirRejCmnt;
    private String cliRejCmnt;
    private Integer optId;
}
