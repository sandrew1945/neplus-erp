package com.neplus.erp.mapper.custom;


import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskProcessBO;
import com.neplus.erp.model.TmClientPO;
import com.neplus.erp.model.TtTaskPO;
import com.neplus.framework.core.mybatis.Pager;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TaskManagerMapper
{
	/**
	 *  Paginated query of the tasks list
	 * @param pager
	 * @return
	 */
	List<TaskManagerBO> taskManagerPageQuery(Pager pager);

	/**
	 *  Get task information by task id
	 * @param taskId
	 * @return
	 */
	TaskManagerBO getTaskInfoById(Integer taskId);

	/**
	 *  Get the processes list of particular task
	 * @param taskId
	 * @return
	 */
	List<TaskProcessBO> getTaskProcessList(Integer taskId);

	/**
	 *	Get all client which didn't create task by specific date.
	 * @param date [mm-yyyy]
	 * @return
	 */
	List<TmClientPO> getNoTaskClientByDate(String date);

	/**
	 *  Get task by client id with year and quarter.
	 * @param clientId
	 * @param quarter
	 * @param year
	 * @return
	 */
	TtTaskPO getTaskByYearAndQuarter(@Param("clientId") Integer clientId, @Param("quarter") String quarter, @Param("year") String year);
}