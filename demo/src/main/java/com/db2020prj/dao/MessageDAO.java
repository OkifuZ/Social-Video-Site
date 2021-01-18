package com.db2020prj.dao;

import com.db2020prj.entities.Message;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message, Integer> {
    public List<Message> findAllByMessageSenderAndMessageReceiver(User messageSender, User messageReceiver);

    public List<Message> findAllByMessageSender(User messageSender);

    public List<Message> findAllByMessageReceiver(User messageReceiver);
}
