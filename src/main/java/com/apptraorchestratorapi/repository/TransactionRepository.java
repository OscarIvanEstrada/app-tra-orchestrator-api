package com.apptraorchestratorapi.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.apptraorchestratorapi.model.Transaction;


public interface TransactionRepository extends MongoRepository<Transaction, String>{

} 