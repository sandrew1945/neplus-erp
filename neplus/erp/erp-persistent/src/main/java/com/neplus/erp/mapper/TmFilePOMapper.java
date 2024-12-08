package com.neplus.erp.mapper;

import com.neplus.erp.model.TmFilePO;
import com.neplus.erp.model.TmFilePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmFilePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    long countByExample(TmFilePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int deleteByExample(TmFilePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int deleteByPrimaryKey(Integer fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int insert(TmFilePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int insertSelective(TmFilePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    List<TmFilePO> selectByExample(TmFilePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    TmFilePO selectByPrimaryKey(Integer fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int updateByExampleSelective(@Param("record") TmFilePO record, @Param("example") TmFilePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int updateByExample(@Param("record") TmFilePO record, @Param("example") TmFilePOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int updateByPrimaryKeySelective(TmFilePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_file
     *
     * @mbg.generated Sat Dec 07 22:21:58 CET 2024
     */
    int updateByPrimaryKey(TmFilePO record);
}