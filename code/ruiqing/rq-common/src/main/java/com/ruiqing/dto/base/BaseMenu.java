package com.ruiqing.dto.base;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 菜单基础信息字段
 * @ClassName BaseMenu.java
 * @Author zhang
 * @Version V1.0
 * @ModificationHistory
 */
public class BaseMenu extends BaseDTO implements Serializable {

    /** 序列号 */
    private static final long serialVersionUID = 4657088302018128532L;

    /** 菜单ID */
    private String menuId;
    // 父级菜单ID
    private String menuModuleId;

    /** 菜单CODE */
    private String menuCode;

    /** 菜单名称 */
    private String menuName;

    /** 菜单actionURL */
    private String actionUrl;

    /** 当前层级 */
    private String curLevel;

    /** 菜单图标样式 **/
    private String iconCls;

    /** 子菜单的列表 */
    private List<BaseMenu> children;

    /** 数量 **/
    private int count;

    private int sort;// 排序
    private Integer level = 1;// 菜单级别
    
    private String attribute;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(String curLevel) {
        this.curLevel = curLevel;
    }

    public List<BaseMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BaseMenu> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMenuModuleId() {
        return menuModuleId;
    }

    public void setMenuModuleId(String menuModuleId) {
        this.menuModuleId = menuModuleId;
    }

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
    
    
}


