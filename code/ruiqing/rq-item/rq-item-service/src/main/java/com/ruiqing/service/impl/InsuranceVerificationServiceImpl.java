package com.ruiqing.service.impl;

import com.ruiqing.dto.RuiqingDTO;
import com.ruiqing.entity.TaskResponseModel;
import com.ruiqing.service.InsuranceVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 18:53
 */
@Service
public class InsuranceVerificationServiceImpl implements InsuranceVerificationService {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceVerificationServiceImpl.class);
    @Override
    public TaskResponseModel<Object> insuranceCheck(String key, RuiqingDTO ruiqingDTO) {
        try {
            //假设耗时50ms
            Thread.sleep(50);
            return TaskResponseModel.success().setKey(key).setData(ruiqingDTO);
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
            return TaskResponseModel.failure().setKey(key).setResultMessage(e.getMessage());
        }
    }
}
