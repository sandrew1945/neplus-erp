package com.neplus.erp.model;

import java.io.Serializable;
import java.util.Date;

public class TrUserRolePO implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.user_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.role_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.create_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.create_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.update_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_user_role.update_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tr_user_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.id
     *
     * @return the value of tr_user_role.id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.id
     *
     * @param id the value for tr_user_role.id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.user_id
     *
     * @return the value of tr_user_role.user_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.user_id
     *
     * @param userId the value for tr_user_role.user_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.role_id
     *
     * @return the value of tr_user_role.role_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.role_id
     *
     * @param roleId the value for tr_user_role.role_id
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.create_by
     *
     * @return the value of tr_user_role.create_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.create_by
     *
     * @param createBy the value for tr_user_role.create_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.create_date
     *
     * @return the value of tr_user_role.create_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.create_date
     *
     * @param createDate the value for tr_user_role.create_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.update_by
     *
     * @return the value of tr_user_role.update_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.update_by
     *
     * @param updateBy the value for tr_user_role.update_by
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_user_role.update_date
     *
     * @return the value of tr_user_role.update_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_user_role.update_date
     *
     * @param updateDate the value for tr_user_role.update_date
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}