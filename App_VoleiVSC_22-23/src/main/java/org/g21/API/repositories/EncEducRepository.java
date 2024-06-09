package org.g21.API.repositories;


import org.g21.API.models.EncEduc;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EncEducRepository extends JpaRepository<EncEduc, Integer> {

    public EncEduc findById(int id);
}
