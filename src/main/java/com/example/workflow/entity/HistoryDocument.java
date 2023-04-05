package com.example.workflow.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.workflow.handlers.MongoDBHistoryEntity;

@Document(collection = "activities")
public class HistoryDocument {

    @Id
    private String id;

    @NotNull
    private MongoDBHistoryEntity historyEntity;

    public HistoryDocument() { }

    public HistoryDocument(MongoDBHistoryEntity historyEntity) {
        this.historyEntity = historyEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MongoDBHistoryEntity getHistoryEntity() {
        return historyEntity;
    }

    public void setHistoryEntity(MongoDBHistoryEntity historyEntity) {
        this.historyEntity = historyEntity;
    }
}
