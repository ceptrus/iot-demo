package com.demo.iot.device.dto;

public class DeviceData {
    private long value;
    private String type;

    /**
     * Private constructor used only to deserialize after being sent
     */
    private DeviceData() {}

    public DeviceData(long value, String type) {
        this.value = value;
        this.type = type;
    }

    public long getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Type: [" + type + "] Value: [" + value + "]";
    }
}
