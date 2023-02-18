package com.apptraorchestratorapi.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.apptraorchestratorapi.model.Process;


public interface ProcessRepository extends MongoRepository<Process, String>{
    @Query("{ 'processName' : { $regex: '(?i)?0' }}")
    public Process findProcessProcessName (String processName);

} 