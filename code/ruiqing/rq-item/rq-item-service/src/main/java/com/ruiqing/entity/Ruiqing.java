package com.ruiqing.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

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



}
