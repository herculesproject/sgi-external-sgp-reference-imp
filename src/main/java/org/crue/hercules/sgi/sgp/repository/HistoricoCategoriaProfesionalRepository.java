package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesionalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoCategoriaProfesionalRepository
    extends JpaRepository<HistoricoCategoriaProfesional, HistoricoCategoriaProfesionalId>,
    JpaSpecificationExecutor<HistoricoCategoriaProfesional> {

}
