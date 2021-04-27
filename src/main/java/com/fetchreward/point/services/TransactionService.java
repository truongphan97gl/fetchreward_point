package com.fetchreward.point.services;

import com.fetchreward.point.domain.AccountBalance;
import com.fetchreward.point.domain.Transaction;
import com.fetchreward.point.domain.TransactionDeduct;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction addTransaction(Transaction transaction);
    List<TransactionDeduct> spendPoint(Integer point);
    List<AccountBalance> getAllBalance();
}
