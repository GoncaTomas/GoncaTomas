package org.g21.API.repositories;

//import java.util.List;

import org.g21.API.models.Message;
//import org.g21.API.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
   // List<Message> findByUser(User user);
}



