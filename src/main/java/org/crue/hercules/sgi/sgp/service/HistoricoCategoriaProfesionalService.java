package org.crue.hercules.sgi.sgp.service;

import java.util.List;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional_;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.repository.HistoricoCategoriaProfesionalRepository;
import org.crue.hercules.sgi.sgp.repository.predicate.HistoricoCategoriaProfesionalPredicateResolver;
import org.crue.hercules.sgi.sgp.repository.specification.HistoricoCategoriaProfesionalSpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HistoricoCategoriaProfesionalService {

  private final HistoricoCategoriaProfesionalRepository repository;

  /**
   * Obtiene las {@link HistoricoCategoriaProfesional} con el id {@link Persona}
   * indicado.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @return la entidad {@link HistoricoCategoriaProfesional}.
   */
  public List<HistoricoCategoriaProfesional> findByPersonaId(String personaId) {
    log.debug("findByPersonaId(String id) - start");
    Specification<HistoricoCategoriaProfesional> specs = HistoricoCategoriaProfesionalSpecifications
        .byPersonaId(personaId);
    final List<HistoricoCategoriaProfesional> returnValue = repository.findAll(specs,
        Sort.by(Sort.Direction.DESC, HistoricoCategoriaProfesional_.FECHA_OBTENCION));
    log.debug("findByPersonaId(String id) - end");
    return returnValue;
  }

  /**
   * Obtiene la {@link HistoricoCategoriaProfesional} con el id {@link Persona}
   * indicado que cumple con el filtro. Si hay varias que lo cumplen devuelve la
   * que tenga la fecha mayor.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @param query     filtros
   * @return la entidad {@link HistoricoCategoriaProfesional}.
   */
  public HistoricoCategoriaProfesional findByPersonaIdAndQuery(String personaId, String query) {
    log.debug("findByPersonaIdAndQuery(String id, String query) - start");
    Pageable pageable = PageRequest.of(0, 1,
        Sort.by(Sort.Direction.DESC, HistoricoCategoriaProfesional_.FECHA_OBTENCION));
    Specification<HistoricoCategoriaProfesional> specs = HistoricoCategoriaProfesionalSpecifications
        .byPersonaId(personaId)
        .and(SgiRSQLJPASupport.toSpecification(query, HistoricoCategoriaProfesionalPredicateResolver.getInstance()));
    final HistoricoCategoriaProfesional returnValue = repository.findAll(specs, pageable).get().findFirst()
        .orElse(null);
    log.debug("findByPersonaIdAndQuery(String id, String query) - end");
    return returnValue;
  }

}
