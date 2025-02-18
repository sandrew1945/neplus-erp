package com.neplus.erp.mapper;

import com.neplus.erp.model.TmRolePO;
import com.neplus.erp.model.TmRolePOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmRolePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    long countByExample(TmRolePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int deleteByExample(TmRolePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int insert(TmRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int insertSelective(TmRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    List<TmRolePO> selectByExample(TmRolePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    TmRolePO selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int updateByExampleSelective(@Param("record") TmRolePO record, @Param("example") TmRolePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int updateByExample(@Param("record") TmRolePO record, @Param("example") TmRolePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int updateByPrimaryKeySelective(TmRolePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_role
     *
     * @mbg.generated Tue Jul 30 15:45:44 CEST 2024
     */
    int updateByPrimaryKey(TmRolePO record);
}