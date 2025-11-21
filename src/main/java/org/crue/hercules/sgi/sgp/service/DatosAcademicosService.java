package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.PersonaNotFoundException;
import org.crue.hercules.sgi.sgp.model.DatosAcademicos;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.repository.DatosAcademicosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DatosAcademicosService {

  private final DatosAcademicosRepository repository;

  public DatosAcademicosService(DatosAcademicosRepository datosAcademicosRepository) {
    this.repository = datosAcademicosRepository;
  }

  /**
   * Crea los datos academicos de una persona
   * 
   * @param personaId       Identificador de la persona
   * @param datosAcademicos Datos academicos de la persona
   * @return los datos academicos
   */
  @Transactional
  public DatosAcademicos create(DatosAcademicos datosAcademicos, String personaId) {
    log.debug("create(DatosAcademicos datosAcademicos, String personaId) - start");
    datosAcademicos.setPersonaId(personaId);
    DatosAcademicos datosAcademicosCreated = repository.save(datosAcademicos);
    log.debug("create(DatosAcademicos datosAcademicos, String personaId) - end");
    return datosAcademicosCreated;
  }

  /**
   * Actualiza los {@link DatosAcademicos} de la {@link Persona}
   * 
   * @param datosAcademicos {@link DatosAcademicos} de la persona actualizada
   * @param personaId       Identificador de la {@link Persona}
   * @return los {@link DatosAcademicos} de la {@link Persona} actualizada
   */
  @Transactional
  public DatosAcademicos update(DatosAcademicos datosAcademicos, String personaId) {
    log.debug("update(DatosAcademicos datosAcademicos, String personaId) - start");

    return repository.findByPersonaId(personaId).map(data -> {
      data.setFechaObtencion(datosAcademicos.getFechaObtencion());
      data.setNivelAcademico(datosAcademicos.getNivelAcademico());

      DatosAcademicos datosAcademicosUpdated = repository.save(data);

      log.debug("update(DatosAcademicos datosAcademicos, String personaId) - end");
      return datosAcademicosUpdated;
    }).orElseThrow(() -> new PersonaNotFoundException(personaId));
  }

  /**
   * Obtiene una entidad {@link DatosAcademicos} con el id {@link Persona}
   * indicado.
   * 
   * @param id Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosAcademicos}.
   */
  public DatosAcademicos findByPersonaId(String id) {
    log.debug("findByPersonaId(String id) - start");
    final DatosAcademicos returnValue = repository.findByPersonaId(id).orElse(null);
    log.debug("findByPersonaId(String id) - end");
    return returnValue;
  }

  /**
   * Comprueba si existen {@link DatosAcademicos} para la {@link Persona}
   * 
   * @param id Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosAcademicos} o
   *         <code>false</code> si no los tiene
   */
  public boolean existsByPersonaId(String id) {
    log.debug("existsByPersonaId(String id) - start");
    final boolean returnValue = repository.existsByPersonaId(id);
    log.debug("existsByPersonaId(String id) - end");
    return returnValue;
  }

}
