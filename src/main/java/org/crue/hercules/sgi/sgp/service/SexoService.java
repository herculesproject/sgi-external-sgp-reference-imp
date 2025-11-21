package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.model.Sexo;
import org.crue.hercules.sgi.sgp.model.Sexo_;
import org.crue.hercules.sgi.sgp.repository.SexoRepository;
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
public class SexoService {

  private final SexoRepository repository;

  /**
   * Obtiene todas las entidades {@link Sexo} ordenadas alfabéticamente.
   *
   * @return el listado de entidades {@link Sexo} ordenadas
   *         alfabéticamente.
   */
  public Page<Sexo> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<Sexo> returnValue = new PageImpl<>(repository.findAll(Sort.by(Sort.Direction.ASC, Sexo_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

}
