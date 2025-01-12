package com.neplus.erp.bean.mailmanager;

import lombok.Data;

@Data
public class MailManagerBO
{
    private Integer templateId;
    private String templateName;
    private Integer templateStatus;
    private String templateContent;
}
