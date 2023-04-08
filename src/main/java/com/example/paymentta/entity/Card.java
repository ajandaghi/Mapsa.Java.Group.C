package com.example.paymentta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Data
@EntityListeners(AuditingEntityListener.class)
public class Card extends AbstractEntity{
   private String cardNumber;
   private String cvv2;
   private Date expDate;

   @OneToOne
   private Account account ;

}
