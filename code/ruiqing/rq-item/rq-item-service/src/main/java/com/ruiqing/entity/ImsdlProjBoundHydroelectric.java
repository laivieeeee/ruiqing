package com.ruiqing.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 */
@Entity(name="imsdl_proj_bound_hydroelectric")
public class ImsdlProjBoundHydroelectric {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@Id
	@Column(name="id")
	private String id;
    /**
     * 项目辅表ID
     */
	@Column(name = "proj_ext_id")
	private String projExtId;
    /**
     * 项目编号（三种规则项目编号）
     */
	@Column(name = "proj_code")
	private String projCode;
    /**
     * 总装机容量
     */
	@Column(name = "total_install_capa")
	private BigDecimal totalInstallCapa;
    /**
     * 业务类型
     */
	@Column(name = "business_type")
	private String businessType;
    /**
     * 计划小时
     */
	@Column(name = "proj_plan_utilhours")
	private BigDecimal projPlanUtilhours;
    /**
     * 市场交易小时
     */
	@Column(name = "proj_mark_utilhours")
	private BigDecimal projMarkUtilhours;
    /**
     * 预计年利用小时
     */
	@Column(name = "estim_hours")
	private BigDecimal estimHours;
    /**
     * 计划电价
     */
	@Column(name = "proj_plan_utilprice")
	private BigDecimal projPlanUtilprice;
    /**
     * 市场交易电价
     */
	@Column(name = "proj_mark_utilprice")
	private BigDecimal projMarkUtilprice;
    /**
     * 综合电价
     */
	@Column(name = "comp_price")
	private BigDecimal compPrice;
    /**
     * 年容量电费
     */
	@Column(name = "year_capacity_electricity_charge")
	private BigDecimal yearCapacityElectricityCharge;
    /**
     * 创建时间
     */
	@Column(name = "create_time")
	private Date createTime;
    /**
     * 创建人userId
     */
	@Column(name = "create_user_id")
	private String createUserId;
    /**
     * 更新时间
     */
	@Column(name = "update_time")
	private Date updateTime;
    /**
     * 更新人userId
     */
	@Column(name = "update_user_id")
	private String updateUserId;
	/**
     * 更新人userId
     */
	@Column(name = "kilow_investamount")
	private String kilowInvestamount;

	/**
	 * 单位千瓦运营成本
	 */
	@Column(name="kilow_opera_costs")
	private BigDecimal kilowOperaCosts;
	/**
	 * 抽水电价
	 */
	@Column(name="pumping_price")
	private BigDecimal pumpingPrice;
	/**
	 * 丰水期电价
	 */
	@Column(name="feng_water_price")
	private BigDecimal fengWaterPrice;
	/**
	 * 平水期电价
	 */
	@Column(name="flat_water_price")
	private BigDecimal flatWaterPrice;
	/**
	 * 枯水期电价
	 */
	@Column(name="dry_water_price")
	private BigDecimal dryWaterPrice;

	public BigDecimal getFengWaterPrice() {
		return fengWaterPrice;
	}

	public void setFengWaterPrice(BigDecimal fengWaterPrice) {
		this.fengWaterPrice = fengWaterPrice;
	}

	public BigDecimal getFlatWaterPrice() {
		return flatWaterPrice;
	}

	public void setFlatWaterPrice(BigDecimal flatWaterPrice) {
		this.flatWaterPrice = flatWaterPrice;
	}

	public BigDecimal getDryWaterPrice() {
		return dryWaterPrice;
	}

	public void setDryWaterPrice(BigDecimal dryWaterPrice) {
		this.dryWaterPrice = dryWaterPrice;
	}

	public BigDecimal getKilowOperaCosts() {
		return kilowOperaCosts;
	}

	public void setKilowOperaCosts(BigDecimal kilowOperaCosts) {
		this.kilowOperaCosts = kilowOperaCosts;
	}

	public BigDecimal getPumpingPrice() {
		return pumpingPrice;
	}

	public void setPumpingPrice(BigDecimal pumpingPrice) {
		this.pumpingPrice = pumpingPrice;
	}

	public String getKilowInvestamount() {
		return kilowInvestamount;
	}

	public void setKilowInvestamount(String kilowInvestamount) {
		this.kilowInvestamount = kilowInvestamount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjExtId() {
		return projExtId;
	}

	public void setProjExtId(String projExtId) {
		this.projExtId = projExtId;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public BigDecimal getTotalInstallCapa() {
		return totalInstallCapa;
	}

	public void setTotalInstallCapa(BigDecimal totalInstallCapa) {
		this.totalInstallCapa = totalInstallCapa;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}


	public BigDecimal getProjPlanUtilhours() {
		return projPlanUtilhours;
	}

	public void setProjPlanUtilhours(BigDecimal projPlanUtilhours) {
		this.projPlanUtilhours = projPlanUtilhours;
	}

	public BigDecimal getProjMarkUtilhours() {
		return projMarkUtilhours;
	}

	public void setProjMarkUtilhours(BigDecimal projMarkUtilhours) {
		this.projMarkUtilhours = projMarkUtilhours;
	}

	public BigDecimal getEstimHours() {
		return estimHours;
	}

	public void setEstimHours(BigDecimal estimHours) {
		this.estimHours = estimHours;
	}

	public BigDecimal getProjPlanUtilprice() {
		return projPlanUtilprice;
	}

	public void setProjPlanUtilprice(BigDecimal projPlanUtilprice) {
		this.projPlanUtilprice = projPlanUtilprice;
	}

	public BigDecimal getProjMarkUtilprice() {
		return projMarkUtilprice;
	}

	public void setProjMarkUtilprice(BigDecimal projMarkUtilprice) {
		this.projMarkUtilprice = projMarkUtilprice;
	}

	public BigDecimal getCompPrice() {
		return compPrice;
	}

	public void setCompPrice(BigDecimal compPrice) {
		this.compPrice = compPrice;
	}

	public BigDecimal getYearCapacityElectricityCharge() {
		return yearCapacityElectricityCharge;
	}

	public void setYearCapacityElectricityCharge(BigDecimal yearCapacityElectricityCharge) {
		this.yearCapacityElectricityCharge = yearCapacityElectricityCharge;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}


}
