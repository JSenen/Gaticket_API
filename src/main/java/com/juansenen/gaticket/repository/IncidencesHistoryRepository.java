package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.IncidencesHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidencesHistoryRepository extends CrudRepository<IncidencesHistory, Long> {

    List<IncidencesHistory> findAll();
}
