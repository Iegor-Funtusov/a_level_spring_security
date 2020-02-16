package ua.com.alevel.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ua.com.alevel.security.persistence.entity.Message;
import ua.com.alevel.security.persistence.entity.User;
import ua.com.alevel.security.persistence.repository.MessageRepository;
import ua.com.alevel.security.persistence.repository.UserRepository;
import ua.com.alevel.security.util.SecurityUtil;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public void add(Message msg) {
        System.out.println("msg = " + msg);
        User user = userRepository.findById(SecurityUtil.getUserId()).orElse(null);
        if (user == null) {
            log.error("something problem");
            return;
        }
//        Message message = new Message();
//        message.setContent(msg);
        msg.setUser(user);
        messageRepository.save(msg);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Message> getAllByUser() {
        User user = userRepository.findById(SecurityUtil.getUserId()).orElse(null);
        if (user == null) {
            log.error("something problem");
            return Collections.emptyList();
        }
        return messageRepository.findByUser(user);
    }
}
