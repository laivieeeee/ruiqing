package com.ruiqing.service;

import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.entity.TaskResponseModel;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 17:35
 */

public interface InsuranceVerificationService {
    TaskResponseModel<Object> insuranceCheck(String key, RuiqingDTO ruiqingDTO);
}
