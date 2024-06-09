package org.g21.API.services;

import java.util.List;

//import org.g21.API.models.Atletas;
import org.g21.API.models.Coordenador;
import org.g21.API.models.User;
//import org.g21.API.repositories.AtletasRepository;
import org.g21.API.repositories.CoordenadorRepository;
import org.g21.API.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordenadorService {

    private final UserRepository userRepository;
    private static CoordenadorRepository coordenadorRepository;


    public CoordenadorService(UserRepository userRepository, CoordenadorRepository coordenadorRepository) {
        this.userRepository = userRepository;
        CoordenadorService.coordenadorRepository = coordenadorRepository;
    }

    public Coordenador getCoordenador(Integer idCoordenador) {
        return coordenadorRepository.findById(idCoordenador).orElse(null);
    }

    public List<Coordenador> getListaCoordenador() {
        return coordenadorRepository.findAll();
    }

    public List<Coordenador> listarCoordenador() {
        return coordenadorRepository.findAll();
    }

    public void atualizarCoordenador(Coordenador coordenador) {
        coordenadorRepository.save(coordenador);
    }

    public Coordenador criarCoordenador(Coordenador novoCoordenador) {
        if (novoCoordenador != null) {
            return coordenadorRepository.save(novoCoordenador);
        }
        return null;
    }

    public User verificarLogin(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public User getUser(int idUser) {
        return userRepository.findById(idUser);
    }

    public void deleteCoordenadorById(int id) {
        coordenadorRepository.deleteById(id);
    }

    public void criarUser(User user) {
        userRepository.save(user);
    }

}
