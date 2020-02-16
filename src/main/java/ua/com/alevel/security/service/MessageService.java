package ua.com.alevel.security.service;

import ua.com.alevel.security.persistence.entity.Message;

import java.util.List;

public interface MessageService {

    void add(Message message);
    List<Message> getAll();
    List<Message> getAllByUser();
}
