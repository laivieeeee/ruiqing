package com.ruiqing.common.constans;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 参数属性;
 * GoodProperty
 */
public class GdProperties {

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /** demo的地址 */
    public String getDemoUrl() {
        return properties.getProperty("gd.demo.url");
    }

    /**
     * 获取超级管理员ID
     *
     * @return
     *
     */
    public String getSysSupperAdminId() {
        return properties.getProperty("system.admin.id");
    }

    public String getSysSupperAdminTip() {
        return properties.getProperty("admin.lock.error");
    }


    public String rsaSignKeyPath(){
        return properties.getProperty("maven.rsa.sign.key.path");
    }

    public boolean signOnOff(){
        String onOff = properties.getProperty("maven.rsa.sign.on.off");
        if("true".equals(onOff)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isSign(){
        String sign = properties.getProperty("gateway.sign");
        if(StringUtils.equals(sign, "false")){
            return false;
        } else {
            return true;
        }
    }

    public String getEnv(){
        return properties.getProperty("maven.env");
    }

    public String getRabOrgUrl(){
        return properties.getProperty("rab.org.url");
    }
}
