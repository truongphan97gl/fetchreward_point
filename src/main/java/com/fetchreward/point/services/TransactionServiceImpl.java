package com.fetchreward.point.services;

import com.fetchreward.point.Repository.TransactionRepository;
import com.fetchreward.point.domain.AccountBalance;
import com.fetchreward.point.domain.Transaction;
import com.fetchreward.point.domain.TransactionDeduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepo;


    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        Optional<List<Transaction>> optionalTransactions = transactionRepo.findByPayer(transaction.getPayer());
        if (optionalTransactions.isEmpty()) {
            if (transaction.getPoints() < 0) {
                return null;
            }
            return transactionRepo.save(transaction);
        }
        List<Transaction> transactions = optionalTransactions.get();
        Integer currentBalance = 0;
        for (Transaction t: transactions) {
            currentBalance += t.getPoints();
        }
        if (currentBalance + transaction.getPoints() < 0) {
            return null;
        }
        return transactionRepo.save(transaction);
    }

    @Override
    public List<TransactionDeduct> spendPoint(Integer point) {
        List<Transaction> allTransaction = getAllTransactions();
        allTransaction.sort(Comparator.comparing(Transaction::getTimestamp));
        Integer currentBalance = 0;
        for (Transaction t: allTransaction) {
            currentBalance += t.getPoints();
        }
        if (currentBalance < point) {
            return null;
        }

        HashMap<String, Integer> transaction = new HashMap<>();
            for (int i = 0; i < allTransaction.size(); i++) {
                if (allTransaction.get(i).getPoints() > point) {
                    transaction.put(allTransaction.get(i).getPayer(), transaction.getOrDefault(allTransaction.get(i).getPayer(), 0) + point);
                    allTransaction.get(i).setPoints(allTransaction.get(i).getPoints() - point);
                    transactionRepo.save(allTransaction.get(i));
                    point = 0;
                } else {
                    point -= allTransaction.get(i).getPoints();
                    transaction.put(allTransaction.get(i).getPayer(), transaction.getOrDefault(allTransaction.get(i).getPayer(), 0) + allTransaction.get(i).getPoints());
                    allTransaction.get(i).setPoints(0);
                    transactionRepo.save(allTransaction.get(i));
                }
                if (point == 0) {
                    break;
                }
            }

            List<TransactionDeduct> res = new ArrayList<>();
            transaction.forEach((k,v) -> {
                res.add(new TransactionDeduct(k, -v));
            });
        return res;
    }

    public List<AccountBalance> getAllBalance() {
        List<Transaction> all = transactionRepo.findAll();
        HashMap<String, Integer> seen = new HashMap<>();
        for (Transaction t: all) {
            seen.put(t.getPayer(), seen.getOrDefault(t.getPayer(), 0) + t.getPoints());
        }
        List<AccountBalance> res = new ArrayList<>();
        seen.forEach((k,v) -> {
            res.add(new AccountBalance(k, v));
        });
        return res;
    }
}
