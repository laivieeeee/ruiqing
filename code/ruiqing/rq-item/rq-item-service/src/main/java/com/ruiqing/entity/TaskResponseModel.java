package com.ruiqing.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: Lai JianZheng
 * @Date: 2020/6/12 17:37
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class TaskResponseModel<T extends Object> implements Serializable {
    private String key;           //唯一调用标志
    private String resultCode;    //结果码
    private String resultMessage; //结果信息
    private T data;               //业务处理结果

    public static TaskResponseModel<Object> success() {
        TaskResponseModel<Object> taskResponseModel = new TaskResponseModel<>();
        taskResponseModel.setResultCode("200");
        return taskResponseModel;
    }
    public static TaskResponseModel<Object> failure() {
        TaskResponseModel<Object> taskResponseModel = new TaskResponseModel<>();
        taskResponseModel.setResultCode("400");
        return taskResponseModel;
    }
}
