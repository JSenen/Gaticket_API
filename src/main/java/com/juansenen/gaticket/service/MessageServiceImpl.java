package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.Messages;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.IncidenceRepository;
import com.juansenen.gaticket.repository.MessageRepository;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private IncidenceRepository incidenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Messages> getAll() {
        List<Messages> messagesList = messageRepository.findAll();
        return messagesList;
    }

    @Override
    public Messages saveOne(Messages message) throws EntityNotFound {
        return messageRepository.save(message);
    }

    @Override
    public List<Messages> getAllById(long idIncidence) throws EntityNotFound {
        Incidences incidenceSearch = incidenceRepository.findById(idIncidence).orElseThrow(()-> new EntityNotFound("Incidence not found"));
        List<Messages> messagesList = messageRepository.findByIdOfIncidence(idIncidence);
        return messagesList;
    }

}
