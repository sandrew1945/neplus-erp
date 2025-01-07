package com.neplus.erp.model;

import java.io.Serializable;
import java.util.Date;

public class TmClientPO implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_name
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_type
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer clientType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.declare_period
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer declarePeriod;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.opt_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer optId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.approve_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer approveId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_ict
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientIct;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_vat
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientVat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.service_start
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Date serviceStart;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.service_end
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Date serviceEnd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_tax_no
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientTaxNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_kbk
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientKbk;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_fee
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_mobile
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_tel
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientTel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_email
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.client_address
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String clientAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.description
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.is_delete
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Integer isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.create_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.create_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.update_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tm_client.update_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tm_client
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_id
     *
     * @return the value of tm_client.client_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_id
     *
     * @param clientId the value for tm_client.client_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_name
     *
     * @return the value of tm_client.client_name
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_name
     *
     * @param clientName the value for tm_client.client_name
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_type
     *
     * @return the value of tm_client.client_type
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_type
     *
     * @param clientType the value for tm_client.client_type
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.declare_period
     *
     * @return the value of tm_client.declare_period
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getDeclarePeriod() {
        return declarePeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.declare_period
     *
     * @param declarePeriod the value for tm_client.declare_period
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setDeclarePeriod(Integer declarePeriod) {
        this.declarePeriod = declarePeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.opt_id
     *
     * @return the value of tm_client.opt_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getOptId() {
        return optId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.opt_id
     *
     * @param optId the value for tm_client.opt_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.approve_id
     *
     * @return the value of tm_client.approve_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getApproveId() {
        return approveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.approve_id
     *
     * @param approveId the value for tm_client.approve_id
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setApproveId(Integer approveId) {
        this.approveId = approveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_ict
     *
     * @return the value of tm_client.client_ict
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientIct() {
        return clientIct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_ict
     *
     * @param clientIct the value for tm_client.client_ict
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientIct(String clientIct) {
        this.clientIct = clientIct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_vat
     *
     * @return the value of tm_client.client_vat
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientVat() {
        return clientVat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_vat
     *
     * @param clientVat the value for tm_client.client_vat
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientVat(String clientVat) {
        this.clientVat = clientVat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.service_start
     *
     * @return the value of tm_client.service_start
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Date getServiceStart() {
        return serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.service_start
     *
     * @param serviceStart the value for tm_client.service_start
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.service_end
     *
     * @return the value of tm_client.service_end
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Date getServiceEnd() {
        return serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.service_end
     *
     * @param serviceEnd the value for tm_client.service_end
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setServiceEnd(Date serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_tax_no
     *
     * @return the value of tm_client.client_tax_no
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientTaxNo() {
        return clientTaxNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_tax_no
     *
     * @param clientTaxNo the value for tm_client.client_tax_no
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientTaxNo(String clientTaxNo) {
        this.clientTaxNo = clientTaxNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_kbk
     *
     * @return the value of tm_client.client_kbk
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientKbk() {
        return clientKbk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_kbk
     *
     * @param clientKbk the value for tm_client.client_kbk
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientKbk(String clientKbk) {
        this.clientKbk = clientKbk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_fee
     *
     * @return the value of tm_client.client_fee
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientFee() {
        return clientFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_fee
     *
     * @param clientFee the value for tm_client.client_fee
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientFee(String clientFee) {
        this.clientFee = clientFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_mobile
     *
     * @return the value of tm_client.client_mobile
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientMobile() {
        return clientMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_mobile
     *
     * @param clientMobile the value for tm_client.client_mobile
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_tel
     *
     * @return the value of tm_client.client_tel
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientTel() {
        return clientTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_tel
     *
     * @param clientTel the value for tm_client.client_tel
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientTel(String clientTel) {
        this.clientTel = clientTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_email
     *
     * @return the value of tm_client.client_email
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_email
     *
     * @param clientEmail the value for tm_client.client_email
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.client_address
     *
     * @return the value of tm_client.client_address
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.client_address
     *
     * @param clientAddress the value for tm_client.client_address
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.description
     *
     * @return the value of tm_client.description
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.description
     *
     * @param description the value for tm_client.description
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.is_delete
     *
     * @return the value of tm_client.is_delete
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.is_delete
     *
     * @param isDelete the value for tm_client.is_delete
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.create_date
     *
     * @return the value of tm_client.create_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.create_date
     *
     * @param createDate the value for tm_client.create_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.create_by
     *
     * @return the value of tm_client.create_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.create_by
     *
     * @param createBy the value for tm_client.create_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.update_date
     *
     * @return the value of tm_client.update_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.update_date
     *
     * @param updateDate the value for tm_client.update_date
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tm_client.update_by
     *
     * @return the value of tm_client.update_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tm_client.update_by
     *
     * @param updateBy the value for tm_client.update_by
     *
     * @mbg.generated Wed Dec 25 18:39:15 CET 2024
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}