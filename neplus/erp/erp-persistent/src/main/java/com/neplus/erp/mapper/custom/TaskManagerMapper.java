package com.neplus.erp.mapper.custom;


import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskProcessBO;
import com.neplus.framework.core.mybatis.Pager;
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
}