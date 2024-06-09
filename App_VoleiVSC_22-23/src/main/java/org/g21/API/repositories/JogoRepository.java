package org.g21.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.g21.API.models.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {
    public <S extends Jogo> S save(S jogo);

    public Jogo save(List<Jogo> jogo);
}
