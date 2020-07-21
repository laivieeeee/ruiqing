package com.ruiqing.common.utils;
/**
 * 密码加密配置
 * @author zhang
 *
 */
public class PasswordHash {
	private static String algorithmName;
	private static int hashIterations;

	static{
		//Assert.hasLength(algorithmName, "algorithmName must be MD5、SHA-1、SHA-256、SHA-384、SHA-512");
		setAlgorithmName("MD5");//user algorithm
		setHashIterations(1);//times to apply the algorithm
	}
	public static String getAlgorithmName() {
		return algorithmName;
	}
	public static void setAlgorithmName(String algorithmName1) {
		algorithmName = algorithmName1;
	}
	public static int getHashIterations() {
		return hashIterations;
	}
	public static void setHashIterations(int hashIterations1) {
		hashIterations = hashIterations1;
	}
	
	public static String toHex(Object source, Object salt) {
		return DigestUtils.hashByShiro(algorithmName, source, salt, hashIterations);
	}
	public static void main(String[] args) {
		String salt = "";
		try {
			salt = StringUtil.stringEncryptMD5("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String pwd = "admin";
		PasswordHash ph = new PasswordHash();
		ph.setAlgorithmName("MD5");
		ph.setHashIterations(1);
		System.out.println(">>>>>>"+ph.toHex(pwd,salt));
		
	}
}
