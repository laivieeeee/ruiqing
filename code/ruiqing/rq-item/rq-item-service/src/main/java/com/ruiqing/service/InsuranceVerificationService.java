package com.ruiqing.service;

import com.ruiqing.entity.PolicyModel;
import com.ruiqing.entity.TaskResponseModel;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 17:35
 */

public interface InsuranceVerificationService {
    TaskResponseModel<Object> insuranceCheck(String key, PolicyModel policyModel);
}
