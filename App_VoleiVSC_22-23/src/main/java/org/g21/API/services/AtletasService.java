package org.g21.API.services;

import java.util.List;
//import java.util.Optional;

import org.g21.API.models.Atletas;
import org.g21.API.models.User;
import org.g21.API.repositories.AtletasRepository;
import org.g21.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtletasService {



    @Autowired
    private AtletasRepository atletasRepository;

    @Autowired
    private UserRepository userRepository;



    public Atletas getAtletaById(Integer idAtleta) {
        return atletasRepository.findById(idAtleta).orElse(null);
    }
    

    public List<Atletas> getListaAtletas() {
        return atletasRepository.findAll();
    }

    public List<Atletas> listarAtletas() {
        return atletasRepository.findAll();
    }



    public void criarAtleta(Atletas atleta) {

        atletasRepository.save(atleta);
    }


    public void atualizarAtleta(Atletas atleta) {
        atletasRepository.save(atleta);
    }

    public void deleteAtletaById(int id) {
        atletasRepository.deleteById(id);
    }

    public User getUser(int idUser) {
        return userRepository.findById(idUser);
    }

}
