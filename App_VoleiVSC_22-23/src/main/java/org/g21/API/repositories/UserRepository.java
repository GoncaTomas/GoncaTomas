package org.g21.API.repositories;

import org.g21.API.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUsernameAndPassword(String username, String password);

    User findById(int idUser);

    public User findByUsername(String username);
}
