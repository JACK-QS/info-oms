package com.ndky.infooms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.common.base.Constants;
import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.entity.SysRole;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.mapper.SysMenuMapper;
import com.ndky.infooms.mapper.SysRoleMapper;
import com.ndky.infooms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ndky.infooms.vo.menu.MenuNameVO;
import com.ndky.infooms.vo.menu.MenuVO;
import com.ndky.infooms.vo.menu.SysMenuVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenqingsheng
 * @since 2021-01-21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    private SysMenuMapper sysMenuMapper;

    /**
     * 根据用户名获取菜单vo集合
     *
     * @param username 用户名
     * @return 菜单vo集合
     */
    @Override
    public List<MenuVO> getMenuByUser(String username) {
        for (int i = 0; i < 10; i++) {
            System.out.println(username);
        }
        List<SysMenu> sysMenus = selectListSysMenuByUserName(username);

        List<MenuVO> menuVoList = new ArrayList<>();
        // 取出一级菜单
        List<SysMenu> firstLevel = sysMenus.stream().filter(o -> o.getParentId() == null).collect(Collectors.toList());
        // 拼装二级菜单 Constants.MENU_ICON_PREFIX==layui-icon
        for (SysMenu sysMenu : firstLevel) {
            List<SysMenu> secodeMenuList = new LinkedList<>();
            for (SysMenu menu : sysMenus) {
                if (StringUtils.equals(menu.getParentId(),sysMenu.getId())){
                    secodeMenuList.add(menu);
                }
            }
            menuVoList.add(MenuVO.builder()
                    .name(sysMenu.getMenuName())
                    .code(sysMenu.getMenuCode())
                    .icon(Constants.MENU_ICON_PREFIX + sysMenu.getMenuIcon())
                    .sysMenus(secodeMenuList)
                    .build());
        }
        return menuVoList;
    }

    /**
     * 根据用户名获取菜单集合
     *
     * @param username 用户名
     * @return 菜单集合
     */
    @Override
    public List<SysMenu> findMenuListByUser(String username) {
        List<SysMenu> sysMenus = selectListSysMenuByUserName(username);
        return sysMenus;

//        // 根据用户名获取角色
//        SysRole sysRole = sysMenuMapper.findByName(username);
//        return sysMenuMapper.findByRoleId(sysRole.getRoleId());
    }

    /**
     * 根据页数条数找到一级菜单集合
     *
     * @param page 页数
     * @return 一级菜单
     */
    @Override
    public IPage<SysMenu> findFirstMenu(Page page) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_level","1")
                .orderByAsc("menu_weight");
        return baseMapper.selectPage(page,sysMenuQueryWrapper);
    }

    /**
     * 根据父菜单id找到父菜单集合
     *
     * @param parentId 父菜单
     * @return 父菜单集合
     */
    @Override
    public List<SysMenu> findByParentId(String parentId) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("parent_id",parentId)
                .orderByAsc("menu_weight");
        return baseMapper.selectList(sysMenuQueryWrapper);
    }

    /**
     * 修改菜单
     *
     * @param sysMenu 菜单
     * @return 返回值
     */
    @Override
    public int updateMenu(SysMenu sysMenu) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("id",sysMenu.getId());
        return baseMapper.update(sysMenu,sysMenuQueryWrapper);
    }

    /**
     * 添加菜单
     *
     * @param sysMenu 菜单
     * @return 返回值
     */
    @Override
    public int addMenu(SysMenu sysMenu) {
        return baseMapper.insert(sysMenu);
    }

    /**
     * 查询菜单
     *
     * @param menuName 菜单名称
     * @param menuCode 菜单别名
     * @param menuHref 菜单链接
     * @return 返回值
     */
    @Override
    public SysMenu getByName(String menuName, String menuCode, String menuHref) {
        //return sysMenuMapper.getByName(menuName,menuCode,menuHref);
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_name",menuName)
                .eq("menu_code",menuCode)
                .eq("menu_href",menuHref);
        return baseMapper.selectOne(sysMenuQueryWrapper);
    }

    /**
     * 根据id查询菜单
     *
     * @param id id
     * @return 菜单
     */
    @Override
    public SysMenu getById(String id) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("id",id);
        return baseMapper.selectOne(sysMenuQueryWrapper);
       //return baseMapper.selectById(id);
    }

    /**
     * 获取一级菜单
     *
     * @return 一级菜单
     */
    @Override
    public List<SysMenu> getFirstMenu() {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_level","1")
                .orderBy(true,true,"menu_weight");
        return baseMapper.selectList(sysMenuQueryWrapper);
    }

    /**
     * 获取二级菜单
     */
    @Override
    public List<SysMenu> getSecondMenu() {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_level","2");
        return baseMapper.selectList(sysMenuQueryWrapper);
    }

    /**
     * 根据角色id查询所有菜单
     *
     * @param roleId 角色id
     * @return 菜单
     */
    @Override
    public List<String> getRoleMenu(Long roleId) {


        // 现根据角色id查询菜单id集合
        List<String> allMenuId = sysMenuRoleService.getAllMenuId(roleId);
        // 根据菜单id查询所有菜单
        Iterator<String> iterator = allMenuId.iterator();
        // 创建一个菜单集合
        List<SysMenu> sysMenus = new ArrayList<>();
        // 使用迭代器遍历菜单
        while (iterator.hasNext()){
            // 创建一个选择器
            QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
            sysMenuQueryWrapper.eq("id", iterator.next());
            SysMenu sysMenu = baseMapper.selectOne(sysMenuQueryWrapper);
            sysMenus.add(sysMenu);
        }
        // 创建一个返回值类型
        List<String> strings = new ArrayList<>();
        // 使用迭代器遍历菜单集合插入到返回值类型集合当中
        Iterator<SysMenu> iterator1 = sysMenus.iterator();
        while (iterator1.hasNext()){
            strings.add(iterator1.next().getId());
        }
        return strings;


    }

    /**
     * 获取菜单层级
     *
     * @return 菜单登记
     */
    @Override
    public List<String> getMenuLevel() {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.select("menu_level")
                .orderByAsc("menu_level");
        //return sysMenuMapper.getMenuLevel();
        List<SysMenu> sysMenus = baseMapper.selectList(sysMenuQueryWrapper);
        //List<String> strings = JSON.parseArray(JSON.toJSONString(sysMenus), String.class);
        Iterator<SysMenu> iterator = sysMenus.iterator();
        List<String> strings = new ArrayList<>();
        while(iterator.hasNext()){
            String s = iterator.next().getMenuLevel().toString();
            strings.add(s);
        }
        return strings;
    }

    /**
     * 查询当前菜单的上级菜单
     *
     * @param menuLevel 上级菜单层级
     * @return 上级菜单名称
     */
    @Override
    public List<MenuNameVO> getPreviousMenu(String menuLevel) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_level",menuLevel)
                .orderByAsc("ctime");
        List<SysMenu> sysMenus = baseMapper.selectList(sysMenuQueryWrapper);
        List<MenuNameVO> menuNameVO = JSON.parseArray(JSON.toJSONString(sysMenus), MenuNameVO.class);
        return menuNameVO;
    }

    /**
     * 根据菜单名称查询菜单id
     *
     * @param menuNames 菜单名称
     * @return 菜单id
     */
    @Override
    public String getByMenuName(String menuNames) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("menu_name",menuNames);

        return baseMapper.selectOne(sysMenuQueryWrapper).getId();
    }

    /**
     * 根据id删除菜单
     *
     * @param id id
     * @return 返回值
     */
    @Override
    public int deleteMenuById(String id) {
        QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
        sysMenuQueryWrapper.eq("id",id)
                .or()
                .eq("parent_id",id);
        return baseMapper.delete(sysMenuQueryWrapper);
    }

    /**
     * 常用
     * 根据用户名字获取菜单列表集合
     */
    List<SysMenu> selectListSysMenuByUserName(String username){
        SysUser byName = sysUserService.findByName(username);

        // 获得用户id
        Long sysId = byName.getSysId();

        // 根据用户id获取角色id
        Long aLong = sysUserRoleService.selectRoleId(sysId);


        // 根据角色id获取菜单id集合
        List<String> allMenuId = sysMenuRoleService.getAllMenuId(aLong);

        // 根据菜单id获取出所有菜单
        List<SysMenu> sysMenus = new ArrayList<>();

        Iterator<String> iterator = allMenuId.iterator();
        while (iterator.hasNext()){
            QueryWrapper<SysMenu> sysMenuQueryWrapper = new QueryWrapper<>();
            sysMenuQueryWrapper.eq("id",iterator.next());
            SysMenu sysMenu = baseMapper.selectOne(sysMenuQueryWrapper);
            sysMenus.add(sysMenu);
        }
        // 对list集合进行排序
        Collections.sort(sysMenus, new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                int i = o1.getMenuWeight() - o2.getMenuWeight();
                if(i == 0){
                    return o1.getMenuWeight() - o2.getMenuWeight();
                }
                return i;
            }
        });

        return sysMenus;
    }
}
