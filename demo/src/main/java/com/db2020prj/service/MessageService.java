package com.db2020prj.service;

import com.db2020prj.dao.MessageDAO;
import com.db2020prj.entities.Message;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    public List<Message> findAllMessagesByRelatedUsers(User user1, User user2) {
        List<Message> messages = messageDAO.findAllByMessageSenderAndMessageReceiver(user1, user2);
        messages.addAll(messageDAO.findAllByMessageSenderAndMessageReceiver(user2, user1));
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message message, Message t1) {
                return message.getMessageSendDate().compareTo(t1.getMessageSendDate());
            }
        });
        return messages;
    }

    public List<Message> findAllMessagesByRelatedUser(User user) {
        List<Message> messages = messageDAO.findAllByMessageSender(user);
        messages.addAll(messageDAO.findAllByMessageReceiver(user));
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message message, Message t1) {
                return message.getMessageSendDate().compareTo(t1.getMessageSendDate());
            }
        });
        return messages;
    }

    public void sendMessage(String messageContent, User sender, User receiver) {
        Message newMessage = new Message();
        newMessage.setMessageContent(messageContent);
        newMessage.setMessageSender(sender);
        newMessage.setMessageReceiver(receiver);
        messageDAO.save(newMessage);
    }
}
