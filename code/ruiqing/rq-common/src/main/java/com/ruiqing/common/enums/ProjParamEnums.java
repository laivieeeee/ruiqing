package com.ruiqing.common.enums;

/**
 * 
 * @author: oujuntian
 *
 * @date: 2019年9月12日
 *
 * @description:
 */
public enum ProjParamEnums {
	INVESTTYPE_JJ("JJ","基建"),
	INVESTTYPE_GQ("GQ","股权并购"),
	INVESTTYPE_SG("SG","资产收购"),
	INVESTTYPE_JG("JG","技改项目"),
	INVESTTYPE_DX("DX","单项房产/土地购置"),
	INVESTTYPE_WX("WX","无形资产（不含土地）"),
	INVESTTYPE_XX("XX","信息化项目"),
	INVESTTYPE_YF("YF","其他研发项目"),
	INVESTTYPE_CZ("CZ","资产处置"),
    INVESTTYPE_QG("QG","其他固定资产投资"),
    INVESTTYPE_QQ("QQ","其他股权投资"),
    INVESTTYPE_QJ("QJ","其他经常性支出"),
	
    PROJSTAGE_PH01("PH01", "储备阶段"), 
    PROJSTAGE_PH02("PH02", "立项阶段"), 
    PROJSTAGE_PH03("PH03", "前期工作"), 
    PROJSTAGE_PH04("PH04", "决策阶段"), 
    PROJSTAGE_PH05("PH05", "投中阶段"), 
    PROJSTAGE_PH06("PH06", "投后阶段"),
    
    STAGETYPE_RESERVE("RESERVE","储备"),
    
    STAGETYPE_APPRREQ("APPRREQ","立项申请"),
    STAGETYPE_APPRFILE("APPRFILE","立项备案"),
    STAGETYPE_APPRBOARD("APPRBOARD","董事会立项"),
    STAGETYPE_PREAPPRBOARD("PREAPPRBOARD","预立项申请"),
    
    STAGETYPE_PREWORK("PREWORK","前期工作"),
    
    STAGETYPE_POLICYREQ("POLICYREQ","决策申请"),
    STAGETYPE_POLICYFILE("POLICYFILE","决策备案"),
    STAGETYPE_POLICYBOARD("POLICYBOARD","董事会决策"),
    
    STAGETYPE_BUILD("BUILD","投中"),
    STAGETYPE_POLICYAGAIN("POLICYAGAIN","投资再决策"),
    
    STAGETYPE_OPERATE("OPERATE","投后"),
    
    PROJTYPE_RESERVE("10","机会储备"),
    PROJTYPE_PREAPPR("20","预立项"),
    PROJTYPE_APPROV("30","立项"),
    
    SAVETYPE_INSERT("INSERT","新增"),
    SAVETYPE_UPDATE("UPDATE","修改"),
    
    PROJSTATUSCHANGE_RECOVER("RECOVER","项目恢复"),
    PROJSTATUSCHANGE_END("END","项目终止"),
    PROJSTATUSCHANGE_QUIT("QUIT","项目退出"),
    PROJSTATUSCHANGE_SUSPEND("SUSPEND","项目中止"),
    
    PROJSTATUS_DRAFT("DRAFT","储备已保存"),
    PROJSTATUS_ESTA("ESTA","储备已立项"),
    PROJSTATUS_PROP("PROP","前期准备中"),
    PROJSTATUS_APPR("APPR","前期已核准"),
    PROJSTATUS_START("START","投中已开工"),
    PROJSTATUS_PRESTART("PRESTART","投中待开工"),
    PROJSTATUS_PROD("PROD","投中已投产"),
    PROJSTATUS_PRESIGN("PRESIGN","投中待签约"),
    PROJSTATUS_SIGN("SIGN","投中已签约"),
    PROJSTATUS_DELI("DELI","投中已交割"),
    PROJSTATUS_QT("QT","投中其他"),
    PROJSTATUS_PREEVAL("PREEVAL","投后待评价"),
    PROJSTATUS_EVALING("EVALING","投后评价中"),
    PROJSTATUS_EVAL("EVAL","投后已评价"),
    PROJSTATUS_PROCESS("PROCESS","取流程状态"),
    
    PROJEXT_ISBOARD_Y("Y","直接董事会审批"),
    PROJEXT_ISBOARD_N("N","不直接董事会审批"),
    
    PROJCODE_SEQSUFFIX("_CODE_SEQ","序列号后缀"),
    PROJCODE_RESETFREQUENCY("NEVER","从不"),
    PROJCODE_SEQY("Y","预立项前缀缀"),
    PROJCODE_SEQOP("OP","机会储备前缀"),
    PROJCODE_SEQ("","立项缀"),
    PACKAGECODE_SEQ("PACKAGE","资产包前缀"),
    
    CONSTRUCTYPE_EXTEND("EXTEND","扩建"),
	CONSTRUCTYPE_NEW("NEW","新建"),
    
    LIMITFACT_TYPE_FD("IMS_PROJECT_LIST_FD","限制性因素调查（陆上风电）"),
    LIMITFACT_TYPE_GF("IMS_PROJECT_LIST_GF","限制性因素调查（光伏）"),
	IMS_PROJECT_LIST_HSFD("IMS_PROJECT_LIST_HSFD","限制性因素调查（海上风电）"),
    PROJ_Y("Y","是"),
    PROJ_N("N","否"),
    ENABLE_Y("Y", "未删除"),
    ENABLE_N("N", "已删除"),
    
    IMS_ROLE_ADMIN("IMS_ROLE_ACCESS_ADMIN","管理员不限权限"),
    IMS_ROLE_LEADER("IMS_ROLE_ACCESS_LEADER","管理团队部门权限"),

    REPORT_TYPE_RT01("RT01","投资完成情况表"),
    REPORT_TYPE_RT02("RT02","股权项目信息收集"),
    REPORT_TYPE_RT03("RT03","固定资产项目信息收集"),
    REPORT_TYPE_RT04("RT04","基建项目执行统计表"),
    REPORT_TYPE_RT05("RT05","股权项目执行统计表"),
    REPORT_TYPE_RT06("RT06","资本支出统计表"),
    REPORT_TYPE_RT07("RT07","年度资本统计表"),
    ;
	
    public final String code;
    public final String name;

    private ProjParamEnums(String code, String name) {
      this.code = code;
      this.name = name; 
    }

    public String getCode()
    {
      return this.code;
    }
    public String getName() {
      return this.name;
    }

    public static ProjParamEnums getProjParam(String code) {
      for (ProjParamEnums operater : values()) {
        if (operater.getCode().equalsIgnoreCase(code)) {
          return operater;
        }
      }

      return null;
    }
}
