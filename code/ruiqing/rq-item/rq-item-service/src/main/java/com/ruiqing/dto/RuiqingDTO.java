package com.ruiqing.dto;

import com.ruiqing.entity.Ruiqing;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 
 * @author zouwanchen
 * @since 2019-10-24
 *
 */
@ApiModel(description="")
@Data
public class RuiqingDTO extends Ruiqing {
	private String httpReqId;
	private String reqUrl;
	private String reqData;
}