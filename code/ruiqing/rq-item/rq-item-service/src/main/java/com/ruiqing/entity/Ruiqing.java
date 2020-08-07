package com.ruiqing.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ruiqing_info")
@Data
public class Ruiqing extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@Id
	@Column(name="ruiqing_id")
	private String ruiqingId;
    /**
     * name
     */
	@Column(name = "name")
	private String name;

    /**
     * code
     */
	@Column(name = "code")
	private String code;

    /**
     * pinyin
     */
	@Column(name = "pinyin")
	private String pinyin;

    /**
     * status
     */
	@Column(name = "status")
	private String status;



}
