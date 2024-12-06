package com.neplus.erp.param;

import com.neplus.erp.service.util.MenuNode;
import lombok.Data;

import java.util.List;

/**
 * Created by summer on 2020/2/1.
 */
@Data
public class PermissionParam
{
    public Integer roleId;

    public List<MenuNode> node;
}
