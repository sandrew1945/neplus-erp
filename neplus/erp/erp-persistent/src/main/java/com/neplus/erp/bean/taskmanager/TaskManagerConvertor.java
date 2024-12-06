package com.neplus.erp.bean.taskmanager;

import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskManagerDTO;
import com.neplus.erp.bean.taskmanager.TaskVO;
import com.neplus.erp.model.TmTaskPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName TaskManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface TaskManagerConvertor
{
    TaskVO toTaskVO(TaskManagerBO TaskManagerBO);

    TmTaskPO toTaskPO(TaskManagerBO TaskManagerBO);

    TmTaskPO toTaskPO(TaskManagerDTO TaskManagerDTO);

    TaskManagerBO toTaskManagerBO(TmTaskPO tmTaskPO);

    List<TaskVO> toTaskVO(List<TaskManagerBO> TaskManagerBOList);
}
