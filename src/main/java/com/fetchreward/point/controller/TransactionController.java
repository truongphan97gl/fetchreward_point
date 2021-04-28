package com.fetchreward.point.controller;

import com.fetchreward.point.domain.AccountBalance;
import com.fetchreward.point.domain.Point;
import com.fetchreward.point.domain.Transaction;
import com.fetchreward.point.domain.TransactionDeduct;
import com.fetchreward.point.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getAllTransaction")
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction =  transactionService.addTransaction(transaction);
        if (savedTransaction == null) {
            return new ResponseEntity<>("Invalid point", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedTransaction, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addListTransaction")
    public ResponseEntity<?> addListTransaction(@RequestBody Transaction[] transactions) {
        for (Transaction t: transactions) {
            Transaction savedTransaction =  transactionService.addTransaction(t);
            if (savedTransaction == null) {
                return new ResponseEntity<>("Invalid point", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping("/spendPoints")
    public ResponseEntity<?> spendPoint(@RequestBody Point point) {
        List<TransactionDeduct> listOfTransaction =  transactionService.spendPoint(point.getPoints());
        if (listOfTransaction == null) {
            return new ResponseEntity<>("Invalid point", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listOfTransaction, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBalance")
    public ResponseEntity<?> getBalance() {
        List<AccountBalance> list = transactionService.getAllBalance();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }
}
