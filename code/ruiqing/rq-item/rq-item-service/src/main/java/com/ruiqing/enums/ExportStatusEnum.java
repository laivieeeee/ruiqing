package com.ruiqing.enums;

public enum ExportStatusEnum {
    STATUS_FAIL("-1","失败"),
    STATUS_TODO("0", "未开始"),
    STATUS_DOING("1","进行中"),
    STATUS_DONE("2", "完成");

    private final String statusType;

    private final String statusName;

    ExportStatusEnum(String statusType, String statusName) {
        this.statusType = statusType;

        this.statusName = statusName;
    }


    public String getStatusType() {
        return statusType;
    }

    public String getStatusName() {
        return statusName;
    }

    public static String getNameByType(String type){
        for (ExportStatusEnum item :ExportStatusEnum.values()){
            if (type.equals(item.getStatusType())){
                return item.getStatusName();
            }
        }
        return null;
    }

    public static ExportStatusEnum getExportStatusEnumByType(String type){
        for (ExportStatusEnum item :ExportStatusEnum.values()){
            if (type.equals(item.getStatusType())){
                return item;
            }
        }
        return null;
    }
}
