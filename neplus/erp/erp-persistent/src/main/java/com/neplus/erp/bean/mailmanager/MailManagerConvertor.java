package com.neplus.erp.bean.mailmanager;

import com.neplus.erp.bean.taskmanager.TaskManagerBO;
import com.neplus.erp.bean.taskmanager.TaskVO;
import com.neplus.erp.model.TmTaskPO;
import com.neplus.erp.model.TtMailTemplatePO;
import com.neplus.erp.model.TtTaskPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName TaskManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface MailManagerConvertor
{
    @Mapping(source = "mailManagerBO.templateSubject", target = "templateSubject")
    MailVO toMailVO(MailManagerBO mailManagerBO);

    @Mapping(source = "mailManagerBO.templateSubject", target = "templateSubject")
    TtMailTemplatePO toMailPO(MailManagerBO mailManagerBO);

    @Mapping(source = "mailManagerDTO.templateSubject", target = "templateSubject")
    TtMailTemplatePO toMailPO(MailManagerDTO mailManagerDTO);

    @Mapping(source = "ttMailTemplatePO.templateSubject", target = "templateSubject")
    MailManagerBO toMailManagerBO(TtMailTemplatePO ttMailTemplatePO);

    List<MailVO> toMailVO(List<MailManagerBO> mailManagerBOList);

    List<MailManagerBO> toMailManagerBO(List<TtMailTemplatePO> mailTemplatePOList);
}
