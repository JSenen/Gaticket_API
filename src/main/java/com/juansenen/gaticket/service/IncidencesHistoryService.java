package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.IncidencesHistory;

import java.util.List;

public interface IncidencesHistoryService {
    List<IncidencesHistory> findAll();

    IncidencesHistory saveOne(IncidencesHistory incidencesHistory);
}
