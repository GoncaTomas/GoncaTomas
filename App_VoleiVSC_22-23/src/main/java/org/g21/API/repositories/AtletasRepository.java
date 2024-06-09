package org.g21.API.repositories;

import org.g21.API.models.Atletas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtletasRepository extends JpaRepository<Atletas, Integer> {

        public Atletas findById(int id);
}
