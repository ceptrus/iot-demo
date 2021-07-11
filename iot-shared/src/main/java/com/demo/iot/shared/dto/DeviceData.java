package com.demo.iot.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DeviceData implements Serializable {
    @JsonProperty
    private long value;

    @JsonProperty
    private String type;
}
