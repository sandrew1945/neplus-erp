package com.neplus.erp.bean.clientmanager;

import com.neplus.erp.bean.usermanager.UserManagerBO;
import com.neplus.erp.bean.usermanager.UserManagerDTO;
import com.neplus.erp.bean.usermanager.UserPageQueryVO;
import com.neplus.erp.model.TmClientPO;
import com.neplus.erp.model.TmUserPO;
import com.neplus.erp.model.TmUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface ClientManagerConvertor
{
    ClientVO toClientVO(ClientManagerBO clientManagerBO);

    TmClientPO toClientPO(ClientManagerBO clientManagerBO);

    TmClientPO toClientPO(ClientManagerDTO clientManagerDTO);

    ClientManagerBO toClientManagerBO(TmClientPO tmClientPO);

    List<ClientVO> toClientVO(List<ClientManagerBO> clientManagerBOList);
}
