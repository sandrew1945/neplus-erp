package com.neplus.erp.service.impl;

import com.neplus.erp.dictionary.Fixcode;
import com.neplus.erp.mapper.TmMenuPOMapper;
import com.neplus.erp.mapper.custom.MenuManagerMapper;
import com.neplus.erp.model.TmMenuPO;
import com.neplus.erp.model.TmMenuPOExample;
import com.neplus.erp.service.MenuManagerService;
import com.neplus.erp.service.util.TreeNode;
import com.neplus.framework.core.bean.AclUserBean;
import com.neplus.framework.core.exception.ServiceException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuManagerServiceImpl implements MenuManagerService
{
    @Resource
    private MenuManagerMapper menuManagerMapper;

    @Resource
    private TmMenuPOMapper tmMenuPOMapper;


    @Override
    public List<TreeNode> getMenuTree() throws ServiceException
    {
        try
        {
            List<TmMenuPO> list = menuManagerMapper.getMenuList();
            // 遍历全部菜单，将节点转换为MenuNode,并将子节点放入父节点的children中
            Map<Integer, TreeNode> cache = new HashMap<>();
            List<TreeNode> menuNodeList = list.stream().map(function -> {
                TreeNode node = new TreeNode();
                Map<String, String> meta = new HashMap<>();
                node.setFunctionId(function.getMenuId());
                node.setPath(function.getPath());
                node.setName(function.getTitle());
                node.setIcon(function.getIcon());
                node.setFuncOrder(function.getFuncOrder());
                cache.put(function.getMenuId(), node);
                if (cache.containsKey(function.getFatherId()))
                {
                    cache.get(function.getFatherId()).addChildren(node);
                    return null;
                }
                return node;
            }).filter(menuNode -> null != menuNode).collect(Collectors.toList());
            return menuNodeList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    @Override
    public boolean updateMenu(TreeNode treeNode, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmMenuPO updateMenu = new TmMenuPO();
            updateMenu.setMenuId(treeNode.getFunctionId());

            updateMenu.setPath(treeNode.getPath());
            updateMenu.setTitle(treeNode.getName());
            updateMenu.setIcon(treeNode.getIcon());
            updateMenu.setFuncOrder(treeNode.getFuncOrder());
            updateMenu.setUpdateBy(loginUser.getUserCode());
            updateMenu.setUpdateDate(new Date());
            int count = tmMenuPOMapper.updateByPrimaryKeySelective(updateMenu);
            if (count > 0)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("更新系统菜单失败", e);
        }
    }

    @Override
    public boolean deleteMenuById(Integer functionId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmMenuPO deleteMenu = new TmMenuPO();
            deleteMenu.setMenuId(functionId);
            deleteMenu.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            deleteMenu.setUpdateBy(loginUser.getUserCode());
            deleteMenu.setUpdateDate(new Date());
            int count = tmMenuPOMapper.updateByPrimaryKeySelective(deleteMenu);
            if (count > 0)
            {
                // 删除该菜单下的子菜单
                TmMenuPOExample example = new TmMenuPOExample();
                TmMenuPOExample.Criteria criteria = example.createCriteria();
                criteria.andFatherIdEqualTo(functionId);
                criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
                List<TmMenuPO> children = tmMenuPOMapper.selectByExample(example);

                children.stream().forEach(child -> {
                    try
                    {
                        deleteMenuById(child.getMenuId(), loginUser);
                    }
                    catch (ServiceException e)
                    {
                        log.error(e.getMessage(), e);
                    }
                });
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除系统菜单失败", e);
        }
    }

    @Override
    public boolean createMenu(TreeNode treeNode, Integer fatherId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            // 判断父节点是否存在

//            TmMenuPO father = tmMenuPOMapper.selectByPrimaryKey(fatherId);
//            if (null == father)
//            {
//                throw new ServiceException("创建系统菜单失败:父节点不存在");
//            }
            TmMenuPO menu = new TmMenuPO();
            menu.setPath(treeNode.getPath());
            menu.setTitle(treeNode.getName());
            menu.setIcon(treeNode.getIcon());
            menu.setFuncOrder(treeNode.getFuncOrder());
            menu.setFatherId(fatherId);
            menu.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            menu.setCreateBy(loginUser.getUserCode());
            menu.setCreateDate(new Date());
            int count = tmMenuPOMapper.insertSelective(menu);
            if (count > 0)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
