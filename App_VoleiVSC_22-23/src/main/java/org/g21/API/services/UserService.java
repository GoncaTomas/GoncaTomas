package org.g21.API.services;

import org.g21.API.models.User;
import org.g21.API.repositories.AtletasRepository;
import org.g21.API.repositories.EncEducRepository;
import org.g21.API.repositories.EqTecRepository;
import org.g21.API.repositories.CoordenadorRepository;
import org.g21.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AtletasRepository atletasRepository;

    @Autowired
    EqTecRepository eqTecRepository;

    @Autowired
    CoordenadorRepository coordenadorRepository;

    @Autowired
    EncEducRepository encEducRepository;

    public List<User> listaUsers() {

        return userRepository.findAll();
    }

    public User verificarLogin(String username, String password) {

        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public void criarUser(User user) {
        userRepository.save(user);
    }


    public User getUser(int idUser) {

        return userRepository.findById(idUser);
    }

    public void atualizarUser(User user) {
        userRepository.save(user);
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
}

}
