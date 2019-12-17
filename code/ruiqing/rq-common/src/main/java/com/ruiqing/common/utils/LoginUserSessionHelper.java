package com.ruiqing.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruiqing.common.constans.Constant;
import com.ruiqing.dto.LoginUserDTO;
import com.ruiqing.dto.SysRightBtn;
import com.ruiqing.dto.base.BaseMenu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登陆用户会话信息辅助类
 *
 * @author LUWEIMIAO1
 * @date 2017年10月13日 下午1:58:09
 */
@Component
public class LoginUserSessionHelper {

	private static int sessionTimeout = 1800; // 30 mins

	@Value("${ripples.sessionTimeout:1800}")
	public void setSessionTimeout(int timeout) throws Exception {
		sessionTimeout = timeout;
	}

	/**
	 * 定义记录日志信息
	 */
	protected static Logger logger = LoggerFactory.getLogger(LoginUserSessionHelper.class);

	public static final String JSON_USER_LAST_ACCESS_TIME = "LAST_ACCESS_TIME";
	public static final String JSON_USER_LOGIN_TIME = "LOGIN_TIME";
	public static final String JSON_USER_CODE = "USER_CODE";
	public static final String JSON_USER_ID = "USER_ID";
	public static final String JSON_USER_NAME = "USER_NAME";
	public static final String JSON_USER_IP = "USER_IP";
	public static final String JSON_USER_TOKEN = "USER_TOKEN";

	/**
	 * 设置用户locale
	 *
	 * @param userId
	 * @param locale
	 */
	public static void setLoginUserLocale(String userId, Locale locale) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userId;
		String field = Constant.SYSTEM_USER_LOCALE;
		RedisUtil.addMap(key, field, locale);
	}

	/**
	 * 获取用户locale
	 *
	 * @param userId
	 * @param locale
	 */
	public static Locale getLoginUserLocale(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userId;
		String field = Constant.SYSTEM_USER_LOCALE;
		return RedisUtil.getMapField(key, field, Locale.class);
	}

	/**
	 * 获取用户locale
	 * @param request
	 * @return
	 */
	public static Locale getLoginUserLocale(HttpServletRequest request) {
		String token=request.getHeader(Constants.TOKEN_NAME);
		String userId = getLoginUserIdByToken(token);
		if(logger.isDebugEnabled()){
			logger.debug(" token:{},userId:{}",token,userId);
		}
		return getLoginUserLocale(userId);
	}
	/**
	 * 获取用户locale
	 * @param request
	 * @return
	 */
	public static Locale getLocaleByToken(String token) {
		String userId = getLoginUserIdByToken(token);
		if(logger.isDebugEnabled()){
			logger.debug(" token:{},userId:{}",token,userId);
		}
		return getLoginUserLocale(userId);
	}

	/**
	 * 取得登录用户的名称;userName
	 *
	 * @param request
	 * @return 用户的名称
	 * @throws Exception
	 */
	public static String getLoginUserName(String userid) {
		//SysRegisterUser user = getSystemLoginUser(userid);
		JSONObject user = getSystemLoginUserJson(userid);
		if (user == null) {
			return null;
		}
		//return user.getUserName();
		return user.getString(JSON_USER_NAME);
	}

	/**
	 * 设置当前登陆用户信息到redis
	 *
	 * @param user
	 *            SysRegisterUser
	 */
	/*public static void setSystemLoginUser(SysRegisterUser user) {
		String key = Constant.LOGIN_USER_REDIS_PRE + user.getUserId();
		String field = Constant.SYSTEM_SMGRLOGINUSER;
		RedisUtil.addMap(key, field, user);
	}*/
	public static void setSystemLoginUserToken(String userId,String token) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userId;
		String field = Constant.SYSTEM_LOGINUSER_TOKEN;
		// RedisUtil.hsetObject(key, field, user);
		RedisUtil.addMap(key, field, token);
	}

	public static String getSystemLoginUserToken(String userId) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userId;
		String field = Constant.SYSTEM_LOGINUSER_TOKEN;
		return RedisUtil.getMapField(key, field, String.class);
	}

	/**
	 * 获取用户登录对象 SysRegisterUser
	 *
	 * @param userid
	 *            用户id
	 * @return
	 * @throws Exception
	 *
	 */
	/*public static SysRegisterUser getSystemLoginUser(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_SMGRLOGINUSER;
		SysRegisterUser user = RedisUtil.getMapField(key, field, SysRegisterUser.class);
		return user;
	}*/
	public static JSONObject getSystemLoginUserJson(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE;
		String field = userid;
		JSONObject userJson = RedisUtil.getMapField(key, field, JSONObject.class);
		return userJson;
	}

	/**
	 * 设置当前登陆用户信息到redis
	 *
	 * @param user
	 *            LoginUserDTO
	 */
	/*public static void setLoginUserDTO(LoginUserDTO dto) {
		String key = Constant.LOGIN_USER_REDIS_PRE;
		String[] tokenArray = dto.getToken().split("#");
		String field = tokenArray[1];
		RedisUtil.addMap(key, field, dto);
	}*/
	public static void setLoginUserJSON(String userId, JSONObject user) {
		String key = Constant.LOGIN_USER_REDIS_PRE;
		//String[] tokenArray = user.get(JSON_USER_TOKEN).toString().split("#");
		//String field = tokenArray[1];
		RedisUtil.addMap(key, userId, user);
	}

	/**
	 * 获取用户登录对象 LoginUserDTO
	 *
	 * @param userid
	 *            用户id
	 * @return
	 * @throws Exception
	 *
	 */
	// public static LoginUserDTO getLoginUserDTO(String userid) {
	// if ( StringUtils.isBlank(userid) ){
	// return null;
	// }
	// String key = Constant.LOGIN_USER_REDIS_PRE;
	// String field = userid;
	// LoginUserDTO dto = (LoginUserDTO)RedisUtil.hgetObject(key, field);
	// return dto;
	// }

	/**
	 * 获取用户登录对象 LoginUserDTO
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return LoginUserDTO
	 *
	 */
	public static LoginUserDTO getLoginUserDTO(HttpServletRequest request) {
		String token = request.getHeader("crctoken");
		//return getLoginUserDTOByToken(token);
		JSONObject json = getLoginUserJSONByToken(token);

		LoginUserDTO lud = new LoginUserDTO();

		if(json != null){
			lud.setLastAccessTime(json.getLongValue(JSON_USER_LAST_ACCESS_TIME));
			lud.setLoginTime(json.getLongValue(JSON_USER_LOGIN_TIME));
			lud.setToken(token);
			lud.setUserCode(json.getString(JSON_USER_CODE));
			lud.setUserId(json.getString(JSON_USER_ID));
			lud.setUserIP(json.getString(JSON_USER_IP));
			lud.setUserName(json.getString(JSON_USER_NAME));
		}
		return lud;
	}

	/**
	 * 获取用户登录token
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return String token
	 *
	 */
	public static String getLoginUserToken(HttpServletRequest request) {
		String token = request.getHeader("crctoken");
		if (StringUtils.isBlank(token) || "undefined".equals(token)) {
			return null;
		}
		return token;
	}

	/**
	 * 根据token获取LoginUserDTO
	 *
	 * @param crctoken
	 *            登陆token
	 * @return LoginUserDTO对象
	 */
	/*private static LoginUserDTO getLoginUserDTOByToken(String crctoken) {
		if (StringUtils.isBlank(crctoken) || "undefined".equals(crctoken)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE;
		String[] tokenArray = crctoken.split("#");
		String field = tokenArray[1];
		LoginUserDTO dto = RedisUtil.getMapField(key, field, LoginUserDTO.class);
		return dto;
	}*/
	public static JSONObject getLoginUserJSONByToken(String crctoken) {
		if (StringUtils.isBlank(crctoken) || "undefined".equals(crctoken)) {
			return null;
		}

		String key = Constant.LOGIN_USER_REDIS_PRE;
		// 请求头token由两问部份组成：token和token的md5码组成，用#连接
		String[] tokenArray = crctoken.split("#");
		String token = tokenArray[0];
		// 解释token的用户信息
		String userid = JWTTokenUtil.getUseridByJWTToken(token);

		String field = userid;
		JSONObject json = RedisUtil.getMapField(key, field, JSONObject.class);
		return json;
	}

	/**
	 * 获取所有用户登录对象 LoginUserDTO
	 *
	 * @return List<LoginUserDTO>
	 *
	 */
	/*public static List<LoginUserDTO> getLoginUserDTOAll() {
		List<LoginUserDTO> list = new ArrayList<LoginUserDTO>(500);
		String key = Constant.LOGIN_USER_REDIS_PRE;
		Map<String, LoginUserDTO> map = RedisUtil.getMap(key, LoginUserDTO.class);
		for (Map.Entry<String, LoginUserDTO> entry : map.entrySet()) {
			LoginUserDTO dto = entry.getValue();
			if (dto != null && getSystemLoginUser(dto.getUserId()) != null) {
				list.add(dto);
			}
		}
		return list;
	}*/


	public static List<JSONObject> getLoginUserJsonAll() {
		//hmap   -> Constant.LOGIN_USER_REDIS_PRE :  userId   : JSONObject

		List<JSONObject> list = new ArrayList<JSONObject>();

		String key = Constant.LOGIN_USER_REDIS_PRE;

		Map<String, JSONObject> map = RedisUtil.getMap(key, JSONObject.class);

		for (Map.Entry<String, JSONObject> entry : map.entrySet()) {
			JSONObject json = entry.getValue();
			if (json != null && getSystemLoginUserJson(entry.getKey()) != null) {
				list.add(json);
			}
		}
		return list;
	}


	/**
	 * 设置当前登陆用户当前的角色信息到redis
	 *
	 * @param userId
	 *            用户id
	 * @param surList
	 *            用户角色集合
	 */
	/*public static void setSysUserRoleCurrent(String userid, SysUserRole sysUserRole) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE_CURRENT;
		RedisUtil.addMap(key, field, sysUserRole);
	}*/
	public static void setSysUserRoleCurrent(String userid, String currRole) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE_CURRENT;
		RedisUtil.addMap(key, field, currRole);
	}

	/**
	 * 获取用户登录对象当前的 SysUserRole
	 *
	 * @author zhang
	 * @date 创建时间：2017年7月29日 下午2:50:54
	 * @param request
	 * @return SysUserRole 登录用户角色
	 * @throws Exception
	 *
	 */
	/*public static SysUserRole getSysUserRoleCurrent(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE_CURRENT;
		SysUserRole sysUserRole = RedisUtil.getMapField(key, field, SysUserRole.class);
		return sysUserRole;
	}*/
	public static String getSysUserCurrRoleID(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE_CURRENT;
		String currRole = RedisUtil.getMapField(key, field, String.class);
		String currRoleId=currRole;
		if(StringUtils.isNotBlank(currRole)){
			String[] currRoleArr=currRole.split("#");
			currRoleId=currRoleArr[0];
		}
		return currRoleId;
	}

	public static String getSysUserCurrRoleName(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE_CURRENT;
		String currRole = RedisUtil.getMapField(key, field, String.class);
		String currRoleName=null;
		if(StringUtils.isNotBlank(currRole)){
			String[] currRoleArr=currRole.split("#");
			if(currRoleArr.length>1){
				currRoleName=currRoleArr[1];
			}
		}
		return currRoleName;
	}

	/**
	 * 设置当前登陆用户角色信息到redis
	 *
	 * @param userId
	 *            用户id
	 * @param surList
	 *            用户角色集合
	 */
	/*public static void setSysUserRole(String userid, List<SysUserRole> surList) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE;
		RedisUtil.addMap(key, field, surList);
	}*/
	public static void setSysUserRole(String userid, Map<String,String> rolesMap) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE;
		RedisUtil.addMap(key, field, rolesMap);
	}

	/**
	 * 获取用户登录对象 SysUserRole
	 *
	 * @author zhang
	 * @date 创建时间：2017年7月29日 下午2:50:54
	 * @param request
	 * @return SysUserRole 登录用户角色
	 * @throws Exception
	 *
	 */
	/*public static List<SysUserRole> getSysUserRole(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE;
		List<SysUserRole> surList = RedisUtil.getMapFieldList(key, field, SysUserRole.class);
		return surList;
	}*/
	public static JSONObject getSysUserRoleJson(String userid) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USERROLE;
		Map<String,String> surMap = RedisUtil.getMapFieldMap(key, field, String.class);
		JSONObject roles = JSONObject.parseObject(JSON.toJSONString(surMap));
		return roles;
	}

	/**
	 * 取得登录用户的ID;
	 *
	 * @param request
	 * @return 登录用户的ID
	 * @throws Exception
	 */
	public static String getLoginUserId(HttpServletRequest request) {
		// 请求头获取crctoken
		String crctoken = getCrcToken(request);
		if (StringUtils.isBlank(crctoken)) {
			return null;
		}
		// 请求头token由两问部份组成：token和token的md5码组成，用#连接
		String[] tokenArray = crctoken.split("#");
		String token = tokenArray[0];
		// 解释token的用户信息
		String userid = JWTTokenUtil.getUseridByJWTToken(token);
		return userid;
	}

	/**
	 * 取得登录用户的ID;
	 *
	 * @param crctoken
	 *            登陆用户token
	 * @return 登录用户的ID
	 * @throws Exception
	 */
	public static String getLoginUserIdByToken(String crctoken) {
		if (StringUtils.isBlank(crctoken)) {
			return null;
		}
		// 请求头token由两问部份组成：token和token的md5码组成，用#连接
		String[] tokenArray = crctoken.split("#");
		String token = tokenArray[0];
		// 解释token的用户信息
		String userid = JWTTokenUtil.getUseridByJWTToken(token);
		return userid;
	}

	/**
	 * 更新redis登陆用户信息过期时间
	 *
	 * @param userId
	 *            用户id
	 */
	// public static void expireLoginUser(String userid){
	// String key = Constant.LOGIN_USER_REDIS_PRE + userid;
	// RedisUtil.expire(key, Constant.EXPIRE_TIME);
	// //更新缓存用户最后访问时间
	// LoginUserDTO loginUserDto = getLoginUserDTO(userid);
	// loginUserDto.setLastAccessTime(new Date());
	// setLoginUserDTO(loginUserDto);
	// }

	/**
	 * 更新redis登陆用户信息过期时间
	 *
	 * @param request
	 *            HttpServletRequest
	 */
	/*public static void expireLoginUser(HttpServletRequest request) {
		String token = getCrcToken(request);
		String userid = getLoginUserIdByToken(token);

		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		RedisUtil.expire(key, Constant.EXPIRE_TIME);
		// 更新缓存用户最后访问时间
		LoginUserDTO loginUserDto = getLoginUserDTOByToken(token);
		loginUserDto.setLastAccessTime(System.currentTimeMillis());
		setLoginUserDTO(loginUserDto);
	}*/

	/*public static void updateUserInfo(HttpServletRequest request) {
		String token = getCrcToken(request);
		String userid = getLoginUserIdByToken(token);

		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		RedisUtil.expire(key, Constant.EXPIRE_TIME);
		// 更新缓存用户最后访问时间
		JSONObject loginUserJson = getLoginUserJSONByToken(token);
		loginUserJson.put(JSON_USER_LAST_ACCESS_TIME,System.currentTimeMillis());
		setLoginUserJSON(userid,loginUserJson);
	}*/

	public static void updateUserSessionAndAccessTime(String userId, JSONObject loginUser) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userId;
		RedisUtil.expire(key, sessionTimeout);
		// 更新缓存用户最后访问时间

		loginUser.put(JSON_USER_LAST_ACCESS_TIME,System.currentTimeMillis());
		setLoginUserJSON(userId,loginUser);
	}

	/**
	 * 更新redis登陆用户信息过期时间
	 *
	 * @param request
	 *            HttpServletRequest
	 */
/*	public static void expireLoginUser(LoginUserDTO loginUserDTO) {
		String key = Constant.LOGIN_USER_REDIS_PRE + loginUserDTO.getUserId();
		RedisUtil.expire(key, Constant.EXPIRE_TIME);
		// 更新缓存用户最后访问时间
		loginUserDTO.setLastAccessTime(System.currentTimeMillis());
		setLoginUserDTO(loginUserDTO);
	}
*/
	/**
	 * 获取请求crctoken
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return String crctoken
	 */
	private static String getCrcToken(HttpServletRequest request) {
		// 请求头获取crctoken
		String token = request.getHeader("crctoken");
		if (StringUtils.isBlank(token)) {
			return null;
		}
		return token;
	}

	/**
	 * 删除在线用户
	 *
	 * @param userid
	 *            在线用户id
	 * @param token
	 *            登陆token,传空时，不删除在线用户
	 */
	/*public static void delLoginUser(String userid, String crctoken) {
		// 清空用户缓存
		RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + userid);
		// 删除用户信息数据
		if (StringUtils.isNotBlank(crctoken)) {
			String md5Token = crctoken;
			String[] tokenArray = crctoken.split("#");
			if (tokenArray.length == 2) {
				md5Token = tokenArray[1];
			}
			RedisUtil.delMapField(Constant.LOGIN_USER_REDIS_PRE, md5Token);
		}
	}*/
	public static void forgeOnlineUser(String userid) {
		markUserState(userid, Constant.SYSTEM_USER_INVALID_REASON, Constant.SYSTEM_USER_PURGED);

		//新方案，强制用户下线会1：清空LOGIN_USER_REDIS_PRE的Map， 2. 删除Constant.LOGIN_USER_REDIS_PRE + userid Map的所有field除了一个mark，用来标识是被强制下线还是timeout
		// 清空用户缓存
		RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + userid);
		// 删除用户信息数据
		RedisUtil.delMapField(Constant.LOGIN_USER_REDIS_PRE, userid);


	}

	public static void markUserState(String userId, String field, String status) {
		String crcToken = getSystemLoginUserToken(userId);
		if(StringUtils.isNotBlank(crcToken)) {
			String key = Constant.LOGIN_USER_REDIS_PRE + crcToken;
			RedisUtil.addMap(key, field, status, sessionTimeout);
		}
	}

	public static boolean checkForge(String userid, HttpServletRequest request, HttpServletResponse response) {
		//新方案 1：LOGIN_USER_REDIS_PRE的Map userid field不存在， 2. Constant.LOGIN_USER_REDIS_PRE + userid Map的所有field除了一个mark都不存在，mark标识forge标识被迫下线

		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_INVALID_REASON;

		String result = RedisUtil.getMapField(key, field, String.class);

		if(result != null && result.equals("forge"))
			return true;

		return false;
	}

	public static boolean checkIfPurged(String crcToken) {
		String key = Constant.LOGIN_USER_REDIS_PRE + crcToken;
		String field = Constant.SYSTEM_USER_INVALID_REASON;
		return Constant.SYSTEM_USER_PURGED.equals(RedisUtil.getMapField(key, field, String.class));
	}

	public static boolean checkIfOffsiteLogon(String crcToken) {
		String key = Constant.LOGIN_USER_REDIS_PRE + crcToken;
		String field = Constant.SYSTEM_USER_INVALID_REASON;
		return Constant.SYSTEM_USER_OFFSITE_LOGON.equals(RedisUtil.getMapField(key, field, String.class));
	}

	public static void cleanTimeoutUser(String userid) {
		//新方案，Timeout user会1：清空LOGIN_USER_REDIS_PRE的Map， 2. 删除Constant.LOGIN_USER_REDIS_PRE + userid Map的所有field
		// 清空用户缓存
		RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + userid);
		// 删除用户信息数据
		RedisUtil.delMapField(Constant.LOGIN_USER_REDIS_PRE, userid);
	}
	public static void userLogout(String userid) {
		String crcToken = getSystemLoginUserToken(userid);
		if(StringUtils.isNotBlank(crcToken)){
			if(RedisUtil.hasFiledInKey(Constant.LOGIN_USER_REDIS_PRE + crcToken, Constant.SYSTEM_USER_INVALID_REASON)){
				RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + crcToken);
			}
		}
		//新方案，logout user会1：清空LOGIN_USER_REDIS_PRE的Map， 2. 删除Constant.LOGIN_USER_REDIS_PRE + userid Map的所有field

		// 清空用户缓存
		RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + userid);
		// 删除用户信息数据
		RedisUtil.delMapField(Constant.LOGIN_USER_REDIS_PRE, userid);
	}

	public static void cleanUserMap(String userid) {
		markUserState(userid, Constant.SYSTEM_USER_INVALID_REASON, Constant.SYSTEM_USER_OFFSITE_LOGON);
		//新方案，new login 之前会1：清空LOGIN_USER_REDIS_PRE的Map， 2. 删除Constant.LOGIN_USER_REDIS_PRE + userid Map的所有field
		// 清空用户缓存
		RedisUtil.del(Constant.LOGIN_USER_REDIS_PRE + userid);
		// 删除用户信息数据
		RedisUtil.delMapField(Constant.LOGIN_USER_REDIS_PRE, userid);

	}


	/**
	 * 获取用户登录账号 userCode
	 *
	 * @param userid
	 *            用户id
	 * @return
	 * @throws Exception
	 *
	 */
	public static String getLoginUserCode(String userid) {
		//SysRegisterUser user = getSystemLoginUser(userid);
		JSONObject user = getSystemLoginUserJson(userid);
		if (user == null) {
			return null;
		}
		//return user.getUserCode();
		return user.getString(JSON_USER_CODE);
	}


	/**
	 * 通过HttpServletRequest返回IP地址
	 *
	 * @param request
	 *            HttpServletRequest
	 * @return ip String
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 设置当前登陆用户的菜单树信息到redis
	 *
	 * @param userId
	 *            用户id
	 * @param 菜单树列表
	 *            菜单集合
	 */
	public static void cacheMenuTree(String userid, String roleId, List<BaseMenu> menuList, String langCode) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_MENU_TREE + roleId+langCode;
		RedisUtil.addMap(key, field, menuList, sessionTimeout);
	}

	/**
	 * 获取当前登陆用户的菜单树信息
	 *
	 * @param userid
	 *            用户id
	 * @return List<BaseMenu> menuList
	 *
	 */
	public static List<BaseMenu> getMenuTree(String userid, String roleId,String langCode) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_MENU_TREE + roleId+langCode;
		List<BaseMenu> menuList = RedisUtil.getMapFieldList(key, field, BaseMenu.class);
		return menuList;
	}

	/**
	 * 清空当前登陆用户的菜单树信息
	 *
	 * @param userid
	 *            用户id
	 * @return
	 *
	 */
	public static void clearMenuTree(String userid, String roleId,String langCode) {
		if (StringUtils.isBlank(userid)) {
			return;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_MENU_TREE + roleId+langCode;
		RedisUtil.delMapField(key, field);
	}

	/**
	 * 设置当前登陆用户的角色的按钮资源
	 *
	 * @param userid
	 * @param roleId
	 * @param list
	 */
	public static void cacheSysRightBtn(String userid, String roleId, List<SysRightBtn> list) {
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_RIGHT_BTN + roleId;
		RedisUtil.addMap(key, field, list, sessionTimeout);
	}

	/**
	 * 获取当前登陆用户的角色的按钮资源
	 *
	 * @param userid
	 * @param roleId
	 * @return
	 */
	public static List<SysRightBtn> getSysRightBtn(String userid, String roleId) {
		if (StringUtils.isBlank(userid)) {
			return null;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_RIGHT_BTN + roleId;
		List<SysRightBtn> menuList = RedisUtil.getMapFieldList(key, field, SysRightBtn.class);
		return menuList;
	}

	/**
	 * 清空当前登陆用户的角色的按钮资源
	 *
	 * @param userid
	 * @param roleId
	 */
	public static void clearSysRightBtn(String userid, String roleId) {
		if (StringUtils.isBlank(userid)) {
			return;
		}
		String key = Constant.LOGIN_USER_REDIS_PRE + userid;
		String field = Constant.SYSTEM_USER_RIGHT_BTN + roleId;
		RedisUtil.delMapField(key, field);
	}

	public static void cacheRetryLimitCredential(String userId, String userCode, AtomicInteger retryCount){
		RedisUtil.addMap(Constant.CRC_RETRY_LIMIT_CREDENTIAL + userId, userCode, retryCount, Constant.EXPIRE_TIME_30_MINS);
	}

	public static void removeRetryLimitCredentialCache(String userId){
		RedisUtil.del(Constant.CRC_RETRY_LIMIT_CREDENTIAL + userId);
	}

	public static AtomicInteger getRetryLimitCredential(String userId, String userCode){
		return RedisUtil.getMapField(Constant.CRC_RETRY_LIMIT_CREDENTIAL + userId, userCode, AtomicInteger.class);
	}

	public static void cacheCaptcha(String cookieValue, String captchaCode){
		RedisUtil.addMap(Constant.CRC_CAPTCHA, cookieValue, captchaCode, Constant.EXPIRE_TIME_1_HOUR);
	}

	public static void removeCaptchaCache(String cookieValue){
		RedisUtil.delMapField(Constant.CRC_CAPTCHA ,cookieValue);
	}

	public static String getCaptcha(String cookieValue){
		return RedisUtil.getMapField(Constant.CRC_CAPTCHA, cookieValue, String.class);
	}

	public static void cacheRepetitiveSubmmission(String userId, String md5Token, String urlWithParams){
		RedisUtil.addMap(Constant.CRC_REPETITIVE_SUBMISSION + userId, Constant.CACHE_KEY_PREFIX + md5Token, urlWithParams, Constant.EXPIRE_TIME_2_MINS);
	}

	public static void removeRepetitiveSubmmissionCache(String userId){
		RedisUtil.del(Constant.CRC_REPETITIVE_SUBMISSION + userId);
	}

	public static String getRepetitiveSubmmission(String userId, String md5Token){
		return RedisUtil.getMapField(Constant.CRC_REPETITIVE_SUBMISSION + userId, Constant.CACHE_KEY_PREFIX + md5Token, String.class);
	}

	public static void cleanExpiredSessions() {
		List<JSONObject> list = getLoginUserJsonAll();
		for (JSONObject json : list) {

			String userId = json.getString(LoginUserSessionHelper.JSON_USER_ID);
			Calendar userCalendar = new GregorianCalendar();
			//最后访问时间
			userCalendar.setTimeInMillis(json.getLongValue(LoginUserSessionHelper.JSON_USER_LAST_ACCESS_TIME));
			//加上有效期30分钟
			userCalendar.add(Calendar.SECOND, sessionTimeout);
			//当前时间
			Calendar currentCalendar = new GregorianCalendar();
			//用户有效时间小于当前时间，session为 已过期
			if (userCalendar.compareTo(currentCalendar) < 0) {
				//删除用户会话信息
				logger.info("====================删除在线用户===================" + userId);
				LoginUserSessionHelper.cleanTimeoutUser(userId);
			}
		}
	}

}
