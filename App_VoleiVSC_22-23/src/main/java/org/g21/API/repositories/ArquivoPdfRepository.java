package org.g21.API.repositories;

import java.util.Optional;

import org.g21.API.models.ArquivoPdf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoPdfRepository extends JpaRepository<ArquivoPdf, Long> {

    Optional<ArquivoPdf> findById(Long id);

}