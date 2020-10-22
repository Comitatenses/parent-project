package com.bilgeadam.commonslib.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private Long id;

    private Long version;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    // could be commented if soft delete is not needed
    private LocalDateTime deleteTime;

    private String insertedBy;

    private String updatedBy;

    // could be commented if soft delete is not needed
    private String deletedBy;
}
