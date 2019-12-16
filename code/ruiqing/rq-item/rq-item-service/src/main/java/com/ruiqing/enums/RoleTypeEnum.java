package com.ruiqing.enums;


/**
 * 角色类型
 * @author zlb
 * @date 2017年9月27日
 */
public enum RoleTypeEnum {
	APP_ADMIN(2,"应用管理员"), 
	PLAT_ADMIN(1, "平台管理员"),
	FUNC_ADMIN(3,"功能管理员"), 
	SYS_ADMIN(-1, "系统管理员");
	
	private final Integer roleType;
	private final String roleName;
	
	RoleTypeEnum(Integer roleType, String roleName){
		this.roleType = roleType;
		this.roleName = roleName;
	}
	
	public Integer getRoleType(){
		return this.roleType;
	}
	
	public String getRoleName(){
		return this.roleName;
	}
	
	public static String getNamebyType(Integer roleType){
		for (RoleTypeEnum item :RoleTypeEnum.values()){
			if (item.getRoleType().compareTo(roleType) == 0){
				return item.getRoleName();
			}
		}
		return "";
	}
	
	public static RoleTypeEnum getRoleEnumByType(Integer roleType){
		for (RoleTypeEnum item :RoleTypeEnum.values()){
			if (item.getRoleType().compareTo(roleType) == 0){
				return item;
			}
		}
		return null;
	}
}
