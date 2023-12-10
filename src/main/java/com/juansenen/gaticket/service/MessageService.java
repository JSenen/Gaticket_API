package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Messages;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface MessageService {
    List<Messages> getAll();

    Messages saveOne(Messages message) throws EntityNotFound;

    List<Messages> getAllById(long idIncidence) throws EntityNotFound;
}
