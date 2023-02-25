package ir.mapsa.project.entity;

import java.sql.Date;

public class Transaction {
    Integer Id;
    Date date;
    String senderCardNumber;
    String recieverCardNumber;
    Long amount;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getRecieverCardNumber() {
        return recieverCardNumber;
    }

    public void setRecieverCardNumber(String recieverCardNumber) {
        this.recieverCardNumber = recieverCardNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
