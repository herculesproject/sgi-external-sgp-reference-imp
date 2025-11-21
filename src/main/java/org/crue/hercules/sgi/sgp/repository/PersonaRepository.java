package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Persona_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonaRepository extends JpaRepository<Persona, String>, JpaSpecificationExecutor<Persona> {

  @Override
  @EntityGraph(attributePaths = { Persona_.TIPO_DOCUMENTO, Persona_.SEXO })
  Page<Persona> findAll(Specification<Persona> spec, Pageable pageable);

}
