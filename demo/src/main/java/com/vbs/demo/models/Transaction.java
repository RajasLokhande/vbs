package com.vbs.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    double amount;
    @Column(nullable = false)
    double currBalance;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    int userId;
    @CurrentTimestamp
    @Column(nullable = false,updatable = false)//cannot override this
    LocalDateTime date;

//    @PrePersist //auto call
//    protected void onCreate()   { //will activate when transaction is created
//        this.date = LocalDateTime.now();
//    } this is same as @CurrentTimeStamp
}
