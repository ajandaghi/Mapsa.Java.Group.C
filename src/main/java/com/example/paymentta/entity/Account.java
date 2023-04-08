package com.example.paymentta.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Data
@EntityListeners(AuditingEntityListener.class)
public class Account extends AbstractEntity{

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    private Customer customer;

    private Long balance;

    @OneToOne(mappedBy = "account")
    private Card card;



}
