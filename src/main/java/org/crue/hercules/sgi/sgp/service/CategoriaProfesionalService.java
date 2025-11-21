package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.CategoriaProfesionalNotFoundException;
import org.crue.hercules.sgi.sgp.model.CategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.CategoriaProfesional_;
import org.crue.hercules.sgi.sgp.repository.CategoriaProfesionalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CategoriaProfesionalService {

  private final CategoriaProfesionalRepository repository;

  public CategoriaProfesionalService(CategoriaProfesionalRepository categoriaProfesionalRepository) {
    this.repository = categoriaProfesionalRepository;
  }

  /**
   * Obtiene una entidad {@link CategoriaProfesional} por id.
   * 
   * @param id identificador de la entidad {@link CategoriaProfesional}.
   * @return la entidad {@link CategoriaProfesional}.
   */
  public CategoriaProfesional findById(String id) {
    log.debug("findById(String id) - start");
    final CategoriaProfesional returnValue = repository.findById(id)
        .orElseThrow(() -> new CategoriaProfesionalNotFoundException(id));
    log.debug("findById(String id) - end");
    return returnValue;
  }

  /**
   * Obtiene todas las entidades {@link CategoriaProfesional} ordenadas
   * alfabéticamente.
   *
   * @return el listado de entidades {@link CategoriaProfesional} ordenadas
   *         alfabéticamente.
   */
  public Page<CategoriaProfesional> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<CategoriaProfesional> returnValue = new PageImpl<>(
        repository.findAll(Sort.by(Sort.Direction.ASC, CategoriaProfesional_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

}
