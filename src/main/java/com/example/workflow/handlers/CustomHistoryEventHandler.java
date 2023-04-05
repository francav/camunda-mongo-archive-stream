package com.example.workflow.handlers;

import java.util.List;

import org.camunda.bpm.engine.impl.history.event.HistoricActivityInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricTaskInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoricVariableUpdateEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.workflow.entity.HistoryDocument;
import com.example.workflow.repository.HistoryDocumentRepository;

@Component
public class CustomHistoryEventHandler implements HistoryEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomHistoryEventHandler.class);

    @Autowired
    private HistoryDocumentRepository historyDocumentRepository;


    @Override
    public void handleEvent(HistoryEvent historyEvent)
    {
        LOGGER.info("Handling custom event...");

        if (historyEvent instanceof HistoricProcessInstanceEventEntity
                || historyEvent instanceof HistoricVariableUpdateEventEntity
                || historyEvent instanceof HistoricTaskInstanceEventEntity
                || historyEvent instanceof HistoricActivityInstanceEventEntity)
        {
            MongoDBHistoryEntity dbHistoryEntity = MongoDBHistoryEntity.createDocumentFromHistoryEvent(historyEvent);
            HistoryDocument historyDocument = new HistoryDocument(dbHistoryEntity);
            historyDocumentRepository.save(historyDocument);
        }
    }

    @Override
    public void handleEvents(List<HistoryEvent> historyEvents)
    {
        for (HistoryEvent historyEvent : historyEvents) {
            handleEvent(historyEvent);
        }
    }
}
