package com.ruiqing.controller;

import com.crc.imsdl.dto.ImsdlOrgJurisdicDTO;
import com.crc.imsdl.entity.ImsdlBaseEntity;
import com.crc.imsdl.service.DataAuthService;
import com.crc.mam.dto.LoginUserDTO;
import com.crc.mam.entity.BaseEntity;
import com.crc.mam.enums.ErrorCodeEnum;
import com.crc.mam.util.LoginUserSessionHelper;
import com.crc.mam.util.Resources;
import com.crc.mam.util.api.ObjectResult;
import com.ruiqing.enums.ErrorCodeEnum;
import com.ruiqing.util.api.ObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 模板实例
 * 
 * @author HUANGLIFEI5
 *
 */
public class BaseController {
	
	@Autowired
	private DataAuthService dataAuthService;

	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * ajax成功
	 * 
	 * @return {Object}
	 */
	public ObjectResult renderSuccess() {
		ObjectResult result = new ObjectResult();
		result.setMsg(Resources.getMessage(ErrorCodeEnum.SUCCESS.getKey()));
		result.setStatusCode(ErrorCodeEnum.SUCCESS.getStatusCode());
		return result;
	}

	/**
	 * ajax成功
	 * 
	 * @param msg
	 *            消息
	 * @return {Object}
	 */
	public ObjectResult renderSuccess(String msg) {
		ObjectResult result = new ObjectResult();
		result.setStatusCode(ErrorCodeEnum.SUCCESS.getStatusCode());
		result.setMsg(msg);
		return result;
	}

	/**
	 * ajax成功
	 * 
	 * @param obj
	 *            成功时的对象
	 * @return {Object}
	 */
	public ObjectResult renderSuccess(Object obj) {
		ObjectResult result = new ObjectResult();
		result.setStatusCode(ErrorCodeEnum.SUCCESS.getStatusCode());
		result.setMsg(Resources.getMessage(ErrorCodeEnum.SUCCESS.getKey()));
		result.setDataResult(obj);
		return result;
	}

	/**
	 * ajax失败
	 * 
	 * @param msg
	 *            失败的消息
	 * @return {Object}
	 */
	public ObjectResult renderError(String msg) {
		ObjectResult result = new ObjectResult();
		result.setMsg(msg);
		return result;
	}

	public ObjectResult renderError(ErrorCodeEnum enumCode) {
		ObjectResult result = new ObjectResult();
		result.setMsg(Resources.getMessage(ErrorCodeEnum.FAIL.getKey()));
		result.setStatusCode(enumCode.getStatusCode());
		return result;
	}

	/**
	 * ajax失败
	 * 
	 * @param msg
	 *            失败的消息
	 * @return {Object}
	 */
	public ObjectResult renderError() {
		ObjectResult result = new ObjectResult();
		result.setMsg(Resources.getMessage(ErrorCodeEnum.FAIL.getKey()));
		return result;
	}

	/**
	 * bean参数验证
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T extends BaseEntity> void beanValidator(T entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
		if (null != constraintViolations) {
			for (ConstraintViolation<T> cv : constraintViolations) {
				throw new IllegalArgumentException(Resources.getMessage(cv.getMessage()));
			}
		}
	}

	/**
	 * 获取当前语言编码
	 * 
	 * @return
	 */
	public String getLocaleLanguageTag() {
		String userId = getLoginUserId();
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		Locale locale = LoginUserSessionHelper.getLoginUserLocale(userId);
		if (null == locale) {
			return null;
		}
		return locale.toLanguageTag();
	}

	/**
	 * 获取当前登入用户ID
	 * 
	 * @return
	 */
	public String getLoginUserId() {
		return LoginUserSessionHelper.getLoginUserId(getRequest());
	}

	/**
	 * 获取当前等人用户角色ID
	 * 
	 * @return
	 */
	public String getLoginUserCurrRoleID() {
		return LoginUserSessionHelper.getSysUserCurrRoleID(getLoginUserId());
	}

	/**
	 * 获取当前登入用户对象
	 * 
	 * @return
	 */
	public LoginUserDTO getLoginUserDTO() {
		return LoginUserSessionHelper.getLoginUserDTO(getRequest());
	}
	
	/**
	 * bean参数验证
	 * 
	 * @param <T>
	 * @param entity
	 */
	public <T extends ImsdlBaseEntity> void dataAuthInit(T entity) {
		String userId = getLoginUserId();
		ImsdlOrgJurisdicDTO dto = new ImsdlOrgJurisdicDTO();
		dto.setLoginUserId(userId);
		
		//设置查询组织权限
		dto.setJurisdicType("ORG");
		List<String> dataAuthOrglist = dataAuthService.queryDataAuthByJuriType(dto);
		if(!CollectionUtils.isEmpty(dataAuthOrglist)) {
			entity.setDataAuthOrgId(dataAuthOrglist.get(0));
		}
		
		//设置查询投资类型
		dto.setJurisdicType("INVESTTYPE");
		List<String> dataAuthInvetypelist = dataAuthService.queryDataAuthByJuriType(dto);
		if(!CollectionUtils.isEmpty(dataAuthInvetypelist)) {
			entity.setDataAuthInvestTypeList(dataAuthInvetypelist);
		}
		
		//设置查询业态
		dto.setJurisdicType("OPERATYPE");
		List<String> dataAuthoperatypelist = dataAuthService.queryDataAuthByJuriType(dto);
		if(!CollectionUtils.isEmpty(dataAuthoperatypelist)) {
			entity.setDataAuthOperaTypeList(dataAuthoperatypelist);
		}
		
		//设置查询境内外
		dto.setJurisdicType("DOMFOREIGN");
		List<String> dataAuthDomForeignlist = dataAuthService.queryDataAuthByJuriType(dto);
		if(!CollectionUtils.isEmpty(dataAuthDomForeignlist)) {
			entity.setDataAuthDomForeignList(dataAuthDomForeignlist);
		}
	}
	
	
	 public String getBasePath() {
	        HttpServletRequest request = this.getRequest();
	        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	            + request.getContextPath() + "/";
	        return basePath;
	    }
}
