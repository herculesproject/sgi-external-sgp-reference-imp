package org.crue.hercules.sgi.sgp.repository;

import org.crue.hercules.sgi.sgp.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TipoDocumentoRepository
    extends JpaRepository<TipoDocumento, String>, JpaSpecificationExecutor<TipoDocumento> {

}
