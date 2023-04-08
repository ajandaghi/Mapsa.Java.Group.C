package com.example.paymentta.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Data
public abstract class AbstractDto {


    private Long id;

    private Integer version;

    private Date insertTimestamp;

    private Date lastUpdateTimestamp;
}
