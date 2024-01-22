package org.learning.springblogricette.repository;

import org.learning.springblogricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
}
