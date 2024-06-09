package org.g21.API.services;

import java.util.List;
import java.util.Optional;

import org.g21.API.models.Treino;
import org.g21.API.repositories.TreinoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    
    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }


    public List<Treino> listarTreinos() {
       return treinoRepository.findAll();
    }

    public Treino criarTreino(Treino novoTreino) {
        if (novoTreino != null) {
            return treinoRepository.save(novoTreino);
        }
        return null;
    }

    public Treino atualizarTreino(Treino treino) {
        Treino treinoAtualizado = treinoRepository.save(treino);
            return treinoAtualizado;
    }

    public void removerTreino(int id) {
        treinoRepository.deleteById(id);
    }
    

    public Optional<Treino> getTreino(int id) {
        return treinoRepository.findById(id);
    }


    public Treino getTreinoById(int id) {
        Optional<Treino> treinoOptional = treinoRepository.findById(id);
        if (treinoOptional.isPresent()) {
            return treinoOptional.get();
        } else {
            return null; 
        }
    }

}
