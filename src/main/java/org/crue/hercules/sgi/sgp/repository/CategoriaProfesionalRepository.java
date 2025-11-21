package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.CategoriaProfesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoriaProfesionalRepository
    extends JpaRepository<CategoriaProfesional, String>, JpaSpecificationExecutor<CategoriaProfesional> {

}
