package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.Colectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColectivoRepository
    extends JpaRepository<Colectivo, String>, JpaSpecificationExecutor<Colectivo> {

}
