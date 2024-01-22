package org.learning.springblogricette.repository;

import org.learning.springblogricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
    List<Ricetta> findByTitleContaining(String search);
}
