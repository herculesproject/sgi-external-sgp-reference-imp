package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.model.TipoDocumento;
import org.crue.hercules.sgi.sgp.model.TipoDocumento_;
import org.crue.hercules.sgi.sgp.repository.TipoDocumentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TipoDocumentoService {

  private final TipoDocumentoRepository repository;

  /**
   * Obtiene todas las entidades {@link TipoDocumento} ordenadas alfabéticamente.
   *
   * @return el listado de entidades {@link TipoDocumento} ordenadas
   *         alfabéticamente.
   */
  public Page<TipoDocumento> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<TipoDocumento> returnValue = new PageImpl<>(
        repository.findAll(Sort.by(Sort.Direction.ASC, TipoDocumento_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

}
