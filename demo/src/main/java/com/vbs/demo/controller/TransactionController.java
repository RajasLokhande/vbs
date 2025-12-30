package com.vbs.demo.controller;

import com.vbs.demo.dto.TransactionDto;
import com.vbs.demo.models.Transaction;
import com.vbs.demo.models.User;
import com.vbs.demo.repositories.TransactionRepo;
import com.vbs.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/deposit")
    public String deposit(@RequestBody TransactionDto obj){
        User user = userRepo.findById(obj.getId()).orElseThrow(()->new RuntimeException("User Not Found"));
        double newBalance = user.getBalance()+obj.getAmount();
        user.setBalance(newBalance);
        userRepo.save(user);

        Transaction t = new Transaction();
        t.setAmount(obj.getAmount());
        t.setCurrBalance(newBalance);
        t.setUserId(obj.getId());
        t.setDescription("Rs "+obj.getAmount()+" Deposit Successful");
        transactionRepo.save(t);


        return "Deposit success";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody TransactionDto obj){
        User user = userRepo.findById(obj.getId()).orElseThrow(()->new RuntimeException("User Not Found"));
        double newBalance = user.getBalance() - obj.getAmount();
        if(newBalance < 0){
            return "Insufficient Balance!";
        }
        user.setBalance(newBalance);
        userRepo.save(user);

        Transaction t = new Transaction();
        t.setAmount(obj.getAmount());
        t.setCurrBalance(newBalance);
        t.setUserId(obj.getId());
        t.setDescription("Rs "+obj.getAmount()+" Withdrawal Successful");
        transactionRepo.save(t);


        return "Withdrawal success";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransactionDto obj)
    {
        User sender = userRepo.findById(obj.getId()).orElseThrow(()->new RuntimeException("User not found"));
        User rec = userRepo.findByUsername(obj.getUsername());

        if(rec == null){return "Invalid user";}
        if(sender.getId() == rec.getId()){return "Self Transaction not allowed";}
        if(obj.getAmount()<1){return "Invalid amount";}

        double sbalance = sender.getBalance() - obj.getAmount();
        double rbalance = rec.getBalance() + obj.getAmount();

        if(sbalance<0){return "Insufficient balance";};

        sender.setBalance(sbalance);
        rec.setBalance(rbalance);
        userRepo.save(sender);
        userRepo.save(rec);


        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();

        t1.setAmount(obj.getAmount());
        t1.setCurrBalance(sbalance);
        t1.setUserId(sender.getId());
        t1.setDescription("Rs "+obj.getAmount()+" Sent to user"+obj.getUsername());

        t2.setAmount(obj.getAmount());
        t2.setCurrBalance(rbalance);
        t2.setUserId(rec.getId());
        t2.setDescription("Rs "+obj.getAmount()+" Received from user "+sender.getUsername());

        transactionRepo.save(t1);
        transactionRepo.save(t2);

        return "Transfer Done Successfully";
    }

    @GetMapping("/passbook/{id}")
    public List<Transaction> getPassbook(@PathVariable int id){   //dynammic array
        return transactionRepo.findByUserId(id);
    }
}






















