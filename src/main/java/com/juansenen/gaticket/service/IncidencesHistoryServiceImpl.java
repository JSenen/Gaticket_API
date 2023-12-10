package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.IncidencesHistory;
import com.juansenen.gaticket.repository.IncidencesHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidencesHistoryServiceImpl implements IncidencesHistoryService{

    @Autowired
    private IncidencesHistoryRepository incidencesHistoryRepository;
    @Override
    public List<IncidencesHistory> findAll() {
        List<IncidencesHistory> historyList = incidencesHistoryRepository.findAll();
        return historyList;
    }

    @Override
    public IncidencesHistory saveOne(IncidencesHistory incidencesHistory) {
        IncidencesHistory history = incidencesHistoryRepository.save(incidencesHistory);
        return history;
    }
}
