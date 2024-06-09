package org.g21.API.services;

import org.g21.API.models.EqTec;
import org.g21.API.models.User;
import org.g21.API.repositories.EqTecRepository;
import org.g21.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EqTecService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EqTecRepository eqTecRepository;
    



    public EqTec getEqTecById(Integer idEqTec) {
        return eqTecRepository.findById(idEqTec).orElse(null);
    }


    public List<EqTec> getListaEqTec() {
        return eqTecRepository.findAll();
    }

    public List<EqTec> listarEqTec() {
        return eqTecRepository.findAll();
    }

    public void atualizareqTec(EqTec eqTec) {
        eqTecRepository.save(eqTec);
    }

    public void criarEqTec(EqTec eqTec) {

        eqTecRepository.save(eqTec);
    }

    public User verificarLogin(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public User getUser(int idUser) {
        return userRepository.findById(idUser);
    }

    public void criarUser(User user) {
        userRepository.save(user);
    }

    public void deleteEqTecById(int id) {
        eqTecRepository.deleteById(id);
    }
}


