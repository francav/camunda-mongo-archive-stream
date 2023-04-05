package com.example.workflow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.workflow.entity.HistoryDocument;

public interface HistoryDocumentRepository extends MongoRepository<HistoryDocument, String> { }
