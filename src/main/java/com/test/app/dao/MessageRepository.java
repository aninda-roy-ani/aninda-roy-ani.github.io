package com.test.app.dao;

import com.test.app.model.Account;
import com.test.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesBySender(String sender);
    List<Message> findMessagesByReceiver(String receiver);

    List<Message> findMessagesBySenderAndReceiver(String sender, String receiver);

}
