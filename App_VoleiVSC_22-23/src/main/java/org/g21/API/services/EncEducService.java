package org.g21.API.services;

import java.util.List;


import org.g21.API.models.EncEduc;
import org.g21.API.models.User;
import org.g21.API.repositories.EncEducRepository;
import org.g21.API.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EncEducService {

    private final UserRepository userRepository;
    private static EncEducRepository encEducRepository;

    public EncEducService(UserRepository userRepository, EncEducRepository encEducRepository) {
        this.userRepository = userRepository;
        EncEducService.encEducRepository = encEducRepository;
    }

    public EncEduc getEncEduc(Integer idEncEduc) {
        return encEducRepository.findById(idEncEduc).orElse(null);
    }



    public static List<EncEduc> getListaEncEduc() {
        return encEducRepository.findAll();
    }

    public List<EncEduc> listarEncEduc() {
        return encEducRepository.findAll();
    }

    public static EncEduc criarEncEduc(EncEduc novoEncEduc) {
        if (novoEncEduc != null) {
            return encEducRepository.save(novoEncEduc);
        }
        return null;
    }

    public User verificarLogin(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public User getUser(int idUser) {
        return userRepository.findById(idUser);
    }

    public void atualizarEncEduc(EncEduc encEduc) {
        encEducRepository.save(encEduc);
    }

    public void deleteEncEducById(int id) {
        encEducRepository.deleteById(id);
    }

    public void criarUser(User user) {
        userRepository.save(user);
    }
}
