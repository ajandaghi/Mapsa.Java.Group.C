package ir.mapsa.project.service;

import ir.mapsa.project.entity.Transaction;

public interface NotifyBase {

    void notify(Transaction transaction, NotifyType type) throws Exception;
}
