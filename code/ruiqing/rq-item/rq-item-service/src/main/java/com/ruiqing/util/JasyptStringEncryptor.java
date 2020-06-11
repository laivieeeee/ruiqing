package com.ruiqing.util;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/11 13:55
 */



import java.nio.charset.StandardCharsets;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/***
 * 配置文件加密工具类<br/>
 * 0.随机生成一个加密密钥key<br/>
 * 1.在工具类main方法中使用new JasyptStringEncryptor(key)实例化加密对象<br/>
 * 2.调用encrypt.encrypt(password=123456)对密码加密生成加密后的密码如:uNEKwHoVRkJhdqbjPrMaUA==<br/>
 * 3.application.yml中添加jasypt.encryptor.password=key<br/>
 * 4.原来application.yml中的密码配置如spring.redis.password=123456改为spring.redis.password=ENC(uNEKwHoVRkJhdqbjPrMaUA==)
 * @author pact
 *1.pom.xml增加配置
 * <dependency>
 * <groupId>com.github.ulisesbocchio</groupId>
 * <artifactId>jasypt-spring-boot-starter</artifactId>
 * <version>2.0.0</version>
 * </dependency>
 *
 * 2.增加JasyptStringEncryptor.java 文件
 * 3.调用JasyptStringEncryptor将密码进行加密
 * 4.在yml文件中，增加配置
 * jasypt:
 * encryptor:
 * password: B2164579180B
 *
 * 并将密码替换成 ENC(vmwy0XS1RLay2q2ec9Oeag==) 即可
 */
public class JasyptStringEncryptor implements StringEncryptor {

    private static final Logger log = LoggerFactory.getLogger(JasyptStringEncryptor.class);

    private final StandardPBEByteEncryptor byteEncryptor;
    private final Base64 base64;

    public static void main(String[] args) {
        StringEncryptor encrypt = new JasyptStringEncryptor("B2164579180B");
        System.out.println(encrypt.decrypt("uNEKwHoVRkJhdqbjPrMaUA=="));
        System.out.println("SIT:");
        System.out.println("rdp:ENC("+encrypt.encrypt("crcloud!")+")");
        System.out.println("dbu:ENC("+encrypt.encrypt("imsdl")+")");
        System.out.println("dbp:ENC("+encrypt.encrypt("Crpower&Ims#2020")+")");
        System.out.println("s3a:ENC("+encrypt.encrypt("LJ34M7GDBE9OYP43316M")+")");
        System.out.println("s3s:ENC("+encrypt.encrypt("0c2NgE5P1BPUI6E62tEC9qiMokDafHDloS6TLNAi")+")");
        System.out.println("emau:ENC("+encrypt.encrypt("crp148gw")+")");
        System.out.println("emap:ENC("+encrypt.encrypt("Crpyushen10")+")");
        System.out.println("bpmpkey:ENC("+encrypt.encrypt("bpmp")+")");
        System.out.println("bpmpsecret:ENC("+encrypt.encrypt("bpmpsit123456")+")");

        System.out.println("UAT:");
        System.out.println("rdp:ENC("+encrypt.encrypt("crcloud!")+")");
        System.out.println("dbu:ENC("+encrypt.encrypt("imsdl")+")");
        System.out.println("dbp:ENC("+encrypt.encrypt("Crpower&Ims#2020")+")");
        System.out.println("s3a:ENC("+encrypt.encrypt("LJ34M7GDBE9OYP43316M")+")");
        System.out.println("s3s:ENC("+encrypt.encrypt("0c2NgE5P1BPUI6E62tEC9qiMokDafHDloS6TLNAi")+")");
        System.out.println("emau:ENC("+encrypt.encrypt("crp148gw")+")");
        System.out.println("emap:ENC("+encrypt.encrypt("Crpyushen10")+")");
        System.out.println("bpmpkey:ENC("+encrypt.encrypt("bpmp")+")");
        System.out.println("bpmpsecret:ENC("+encrypt.encrypt("bpmpuat123456")+")");

        System.out.println("PRD:");
        System.out.println("rdp:ENC("+encrypt.encrypt("crcloud!")+")");
        System.out.println("dbu:ENC("+encrypt.encrypt("imsdlprd")+")");
        System.out.println("dbp:ENC("+encrypt.encrypt("Crp&ImsPrd#2020")+")");
        System.out.println("s3a:ENC("+encrypt.encrypt("0W5R4HER9HB739YS24J2")+")");
        System.out.println("s3s:ENC("+encrypt.encrypt("1wohqyleMrwrx7PgF00QyRKD5UGNrjY4bMJLpOLf")+")");
        System.out.println("emau:ENC("+encrypt.encrypt("crp148gw")+")");
        System.out.println("emap:ENC("+encrypt.encrypt("Crpyushen10")+")");
        System.out.println("bpmpkey:ENC("+encrypt.encrypt("bpmp")+")");
        System.out.println("bpmpsecret:ENC("+encrypt.encrypt("crpower!QAZ")+")");
    }
    public JasyptStringEncryptor() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("EdLKOREFDMI/sddnc@A");
        config.setAlgorithm("PBEWithMD5AndDES");
        this.byteEncryptor = new StandardPBEByteEncryptor();
        this.byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }
    public JasyptStringEncryptor(String password) {
        SimplePBEConfig config = new SimplePBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(password);
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }

    public JasyptStringEncryptor(Environment environment) {
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(getConfig(environment));
        this.base64 = new Base64();
    }

    public JasyptStringEncryptor(SimpleStringPBEConfig config) {
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }

    @Override
    public String encrypt(String s) {
        byte[] encrypt = byteEncryptor.encrypt((s).getBytes());
        byte[] encode = base64.encode(encrypt);
        return new String(encode,StandardCharsets.UTF_8);
    }

    @Override
    public String decrypt(String s) {
        byte[] decode = base64.decode(s.getBytes());
        byte[] decrypt  = byteEncryptor.decrypt(decode);
        return new String(decrypt,StandardCharsets.UTF_8);
    }
    private PBEConfig getConfig(Environment e){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(getRequiredProperty(e, "jasypt.encryptor.password"));
        config.setAlgorithm(getProperty(e, "jasypt.encryptor.algorithm", "PBEWithMD5AndDES"));
        config.setKeyObtentionIterations(getProperty(e, "jasypt.encryptor.keyObtentionIterations", "1000"));
        config.setPoolSize(getProperty(e, "jasypt.encryptor.poolSize", "1"));
        config.setProviderName(getProperty(e, "jasypt.encryptor.providerName", null));
        config.setSaltGeneratorClassName(getProperty(e, "jasypt.encryptor.saltGeneratorClassname", "org.jasypt.salt.RandomSaltGenerator"));
        config.setStringOutputType(getProperty(e, "jasypt.encryptor.stringOutputType", "base64"));
        return config;
    }

    private static String getProperty(Environment environment, String key, String defaultValue) {
        if (!propertyExists(environment, key)) {
            log.info("Encryptor config not found for property {}, using default value: {}", key, defaultValue);
        }
        return environment.getProperty(key, defaultValue);
    }

    private static boolean propertyExists(Environment environment, String key) {
        return environment.getProperty(key) != null;
    }

    private static String getRequiredProperty(Environment environment, String key) {
        if (!propertyExists(environment, key)) {
            throw new IllegalStateException(String.format("Required Encryption configuration property missing: %s", key));
        }
        return environment.getProperty(key);
    }

}