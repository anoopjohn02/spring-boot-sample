package com.anoop.examples.exceptions;

public enum ErrorCodes {

    ACCOUNT_NOT_EXIST("server.error.response.account.not.exist","Account Not Exist"),
    FEATURE_NOT_IMPLEMENTED("server.error.response.feature.not.implemented","Feature Not Implemented"),
    INVENTORY_NOT_FOUND("server.error.response.inventory.not.found","Inventory Not Found"),
    COMPANY_NOT_EXIST("server.error.response.company.not.exist","Company Not Exist"),
    DEVICE_ALREADY_INUSE("server.error.response.device.already.inuse","Device Already Used"),
    DEVICE_NOT_EXIST("server.error.response.device.not.exist","Device Not Exist"),
    DOCUMENT_NOT_EXIST("server.error.response.not.found","Not Found"),
    GROUP_NOT_FOUND("server.error.response.group.not.found","Group Not Found"),
    INTERNAL_SERVER_ERROR("server.error.response.internal.server.error","Internal Server Error"),
    DEVICE_TYPE_NOT_EXIST("server.error.response.device.type.not.exist","Device Type Not Exist"),
    INVALID_ACCESS("server.error.response.access.invalid","Invalid Access"),
    DEVICE_TYPE_ALREADY_INUSE("server.error.response.device.type.already.inuse","Device Type Already Exist"),
    ALERT_NOT_FOUND("server.error.response.alert.not.found","Alert Not Found"),
    OPERATION_NOT_FOUND("server.error.response.operation.not.found","Operation Not Found"),
    NOTIFICATION_NOT_FOUND("server.error.response.alert.not.found","Notification Not Found"),
    EVENT_NOTIFICATION_NOT_FOUND("server.error.response.event.not.found","Event Notification Not Found");

    private final String key;
    private final String description;

    private ErrorCodes(String key, String description){

        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
