package com.ruiqing.enums;

/**
 * 接口响应提示
 * @author Administrator
 * @date  2017年10月11日
 */
public enum SmsCodeEnum {

	STATUS_NULL("-1","参数为空。信息、电话号码等有空指针，登陆失败"),
    STATUS_OVER("-2", "电话号码个数超过100"),
    STATUS_CACHE("-10","申请缓存空间失败"),
    STATUS_NUMF("-11", "电话号码中有非数字字符"),
	STATUS_NUMY("-12", "有异常电话号码"),
	STATUS_NUMN("-13", "电话号码个数与实际个数不相等"),
	STATUS_NUMO("-14", "实际号码个数超过100"),
	STATUS_MSGTO("-101", "发送消息等待超时"),
	STATUS_MSGF("-102", "发送或接收消息失败"),
	STATUS_MSGRT("-103", "接收消息超时"),
	STATUS_QT("-200", "其他错误"),
	STATUS_WEBF("-999", "web服务器内部错误");

    private final String statusCode;
    private final String statusMsg;

    SmsCodeEnum(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public String getStatusCode() {
		return statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}
	
	public static String getMsgBycode(String statusCode){
        for (SmsCodeEnum item :SmsCodeEnum.values()){
            if (statusCode.equals(item.getStatusCode())){
                return item.getStatusMsg();
            }
        }
        return null;
    }
}
