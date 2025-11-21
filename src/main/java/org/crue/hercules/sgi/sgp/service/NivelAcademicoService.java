package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.NivelAcademicoNotFoundException;
import org.crue.hercules.sgi.sgp.model.NivelAcademico;
import org.crue.hercules.sgi.sgp.model.NivelAcademico_;
import org.crue.hercules.sgi.sgp.repository.NivelAcademicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class NivelAcademicoService {

  private final NivelAcademicoRepository repository;

  public NivelAcademicoService(NivelAcademicoRepository nivelAcademicoRepository) {
    this.repository = nivelAcademicoRepository;
  }

  /**
   * Obtiene una entidad {@link NivelAcademico} por id.
   * 
   * @param id identificador de la entidad {@link NivelAcademico}.
   * @return la entidad {@link NivelAcademico}.
   */
  public NivelAcademico findById(String id) {
    log.debug("findById(String id) - start");
    final NivelAcademico returnValue = repository.findById(id)
        .orElseThrow(() -> new NivelAcademicoNotFoundException(id));
    log.debug("findById(String id) - end");
    return returnValue;
  }

  /**
   * Obtiene todas las entidades {@link NivelAcademico} ordenadas alfabéticamente.
   *
   * @return el listado de entidades {@link NivelAcademico} ordenadas
   *         alfabéticamente.
   */
  public Page<NivelAcademico> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<NivelAcademico> returnValue = new PageImpl<>(
        repository.findAll(Sort.by(Sort.Direction.ASC, NivelAcademico_.NOMBRE)));
    log.debug("findAllSorted() - end");
    return returnValue;
  }

}
