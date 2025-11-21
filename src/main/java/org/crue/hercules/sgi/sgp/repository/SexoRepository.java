package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SexoRepository extends JpaRepository<Sexo, String>, JpaSpecificationExecutor<Sexo> {

}
