package ua.com.alevel.security.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.com.alevel.security.persistence.entity.Message;
import ua.com.alevel.security.persistence.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByUser(User user);
}
