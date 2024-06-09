package org.g21.API.repositories;


import org.g21.API.models.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Integer> {

    public Coordenador findById(int id);
}
