package org.g21.API.services;

import java.util.List;
import java.util.Optional;

import org.g21.API.models.Jogo;
import org.g21.API.repositories.JogoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;


    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Jogo criarJogo(Jogo novoJogo) {
        if (novoJogo != null) {
            return jogoRepository.save(novoJogo);
        }
        return null;
    }

    public Jogo atualizarJogo(Jogo jogo) {
        Jogo jogoAtualizado = jogoRepository.save(jogo);
        return jogoAtualizado;
    }

    public void removerJogo(int id) {
        jogoRepository.deleteById(id);
    }
    public Optional<Jogo> getJogo(int id) {
        return jogoRepository.findById(id);
    }


    public Jogo getJogoById(int id) {
        Optional<Jogo> jogoOptional = jogoRepository.findById(id);
        if (jogoOptional.isPresent()) {
            return jogoOptional.get();
        } else {
            return null;

}
}
}