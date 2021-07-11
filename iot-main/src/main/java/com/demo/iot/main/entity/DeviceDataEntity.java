package com.demo.iot.main.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "DeviceData")
@CompoundIndexes({
    @CompoundIndex(name = "idx_type_date", def = "{'type' : 1, 'created': 1}")
})
public class DeviceDataEntity {

    @Id
    private String id;

    @Indexed
    @NonNull
    private final String type;

    @NonNull
    private final long value;

    @CreatedDate
    private Instant created;

}
