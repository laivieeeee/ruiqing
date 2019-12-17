package com.ruiqing.common.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * jst token 工具类
 * 用于登陆生成token及解释token
 * @author LUWEIMIAO1
 * 
 */
public class JWTTokenUtil {
	
	/** 密钥*/
	private  static final String SECURITY_KEY = "895@!$fmty#$%";

	/** 默认token有效时间30分钟 */
	private static final long TIME_LIMIT = 30*60*1000;
	/** token key*/
	public static final String USER_ID = "USER_ID";
	public static final String TENANT_ID = "TENANT_ID";

	/**
	 * 解释jwt token
	 * @param jsonToken jwt token
	 * @return jst Claims对象
	 */
	public static Claims parseJWT(String jsonToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECURITY_KEY))
					.parseClaimsJws(jsonToken).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 从 token获取用户id
	 * @param jsonToken jwt token
	 * @return 用户id
	 */
	public static String getUseridByJWTToken(String jsonToken) {
		Claims claims = parseJWT(jsonToken);
		if ( claims == null ){
			return null;
		}
		String userid = null;
		Object obj = claims.get(USER_ID);
		if ( obj != null ){
			userid = String.valueOf(obj);
		}
	
		return userid;
	}
	
	/**
	 * 生成jwt token
	 * @param userId 用户id或帐号
	 * @return
	 */
	public static String createJWTToken(String userId) {
		return createJWTToken(userId, null);
	}

	/**
	 * 生成token
	 *
	 * @param userId   用户ID
	 * @param tenantId 租户ID
	 * @return
	 */
	public static String createJWTToken(String userId, String tenantId) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		// 生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder()
				.claim(USER_ID, userId);

		if (tenantId != null) {
			builder.claim(TENANT_ID, tenantId);
		}

//		if (ttl > 0) {
//			long now = System.currentTimeMillis();
//			builder.setExpiration(new Date(now + ttl)); // 过期时间
//			builder.setNotBefore(new Date(now)); // 感觉不需要：当节点系统时间中有细微偏差时会认为token无效
//		}

		// 生成JWT
		return builder.signWith(signatureAlgorithm, signingKey).compact();
	}
	
	
	public static void main(String[] args){
//		String token = createJWTToken("useraa");
//		System.out.println(token);
//		
//		Claims claims = parseJWT(token);
//		String userid = getUseridByJWTToken(token);
//		System.out.println(userid);
//		Date exp = claims.getExpiration();
//		System.out.println(claims.toString());
		
	}

}
