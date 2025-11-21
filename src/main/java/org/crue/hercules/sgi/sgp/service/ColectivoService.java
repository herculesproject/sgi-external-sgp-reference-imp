package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.ColectivoNotFoundException;
import org.crue.hercules.sgi.sgp.model.Colectivo;
import org.crue.hercules.sgi.sgp.model.Colectivo_;
import org.crue.hercules.sgi.sgp.repository.ColectivoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ColectivoService {

  private final ColectivoRepository repository;

  /**
   * Obtiene todas las entidades {@link Colectivo} ordenadas alfabéticamente.
   *
   * @return el listado de entidades {@link Colectivo} ordenadas alfabéticamente..
   */
  public Page<Colectivo> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<Colectivo> returnValue = new PageImpl<>(
        repository.findAll(Sort.by(Sort.Direction.ASC, Colectivo_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

  /**
   * Obtiene una entidad {@link Colectivo} por id.
   * 
   * @param id identificador de la entidad {@link Colectivo}.
   * @return la entidad {@link Colectivo}.
   */
  public Colectivo findById(String id) {
    log.debug("findById(String id) - start");
    final Colectivo returnValue = repository.findById(id)
        .orElseThrow(() -> new ColectivoNotFoundException(id));
    log.debug("findById(String id) - end");
    return returnValue;
  }

}
