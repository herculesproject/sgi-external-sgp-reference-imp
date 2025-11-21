package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.NivelAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NivelAcademicoRepository
    extends JpaRepository<NivelAcademico, String>, JpaSpecificationExecutor<NivelAcademico> {

}
