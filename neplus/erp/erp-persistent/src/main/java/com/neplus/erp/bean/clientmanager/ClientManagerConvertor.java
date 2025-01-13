package com.neplus.erp.bean.clientmanager;


import com.neplus.erp.model.TmClientPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperation;

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

    @Mapping(source = "clientManagerBO.serviceStart", target = "serviceStart")
    @Mapping(source = "clientManagerBO.serviceEnd", target = "serviceEnd")
    TmClientPO toClientPO(ClientManagerBO clientManagerBO);

    @Mapping(source = "clientManagerDTO.serviceStart", target = "serviceStart")
    @Mapping(source = "clientManagerDTO.serviceEnd", target = "serviceEnd")
    TmClientPO toClientPO(ClientManagerDTO clientManagerDTO);

    @Mapping(source = "tmClientPO.serviceStart", target = "serviceStart")
    @Mapping(source = "tmClientPO.serviceEnd", target = "serviceEnd")
    @Mapping(source = "tmClientPO.clientLinkman", target = "clientLinkman")
    ClientManagerBO toClientManagerBO(TmClientPO tmClientPO);

    List<ClientVO> toClientVO(List<ClientManagerBO> clientManagerBOList);
}
