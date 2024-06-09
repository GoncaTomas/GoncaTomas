package org.g21.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.g21.API.models.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {
    public <S extends Treino> S save(S treino);

    public Treino save(List<Treino> treino);
}



