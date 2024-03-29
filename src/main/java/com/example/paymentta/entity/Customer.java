package com.example.paymentta.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer extends AbstractEntity {


    private String firstName;
    private String lastName;
    private Integer age;
    private String customerNo;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;





}
