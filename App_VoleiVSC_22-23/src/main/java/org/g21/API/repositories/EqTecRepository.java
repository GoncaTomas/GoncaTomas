package org.g21.API.repositories;

import org.g21.API.models.EqTec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EqTecRepository extends JpaRepository<EqTec, Integer> {

    public EqTec findById(int id);

}
