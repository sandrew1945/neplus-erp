package com.neplus.erp.bean.clientmanager;

import lombok.Data;

import java.util.Date;

@Data
public class ClientVO
{
    private Integer clientId;
    private String clientName;
    private Integer clientType;
    private Integer declarePeriod;
    private Integer optId;
    private String optName;
    private Integer approveId;
    private String approveName;
    private String clientIct;
    private String clientVat;
    private Date serviceStart;
    private Date serviceEnd;
    private String clientTaxNo;
    private String clientKbk;
    private String clientMobile;
    private String clientTel;
    private String clientEmail;
    private String clientAddress;
    private String description;
}
