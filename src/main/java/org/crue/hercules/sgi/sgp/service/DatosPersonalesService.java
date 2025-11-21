package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.PersonaNotFoundException;
import org.crue.hercules.sgi.sgp.model.DatosPersonales;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.repository.DatosPersonalesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DatosPersonalesService {

  private final DatosPersonalesRepository repository;

  public DatosPersonalesService(DatosPersonalesRepository datosPersonalesRepository) {
    this.repository = datosPersonalesRepository;
  }

  /**
   * Crea los datos personales de una persona
   * 
   * @param personaId       Identificador de la persona
   * @param datosPersonales Datos personales de la persona
   * @return los datos personales
   */
  @Transactional
  public DatosPersonales create(DatosPersonales datosPersonales, String personaId) {
    log.debug("create(DatosPersonales datosPersonales) - start");
    datosPersonales.setPersonaId(personaId);
    DatosPersonales datosPersonalesCreated = repository.save(datosPersonales);
    log.debug("create(DatosPersonales datosPersonales) - end");
    return datosPersonalesCreated;
  }

  /**
   * Actualiza los {@link DatosPersonales} de la {@link Persona}
   * 
   * @param datosPersonales {@link DatosPersonales} de la persona actualizada
   * @param personaId       Identificador de la {@link Persona}
   * @return los {@link DatosPersonales} de la {@link Persona} actualizada
   */
  @Transactional
  public DatosPersonales update(DatosPersonales datosPersonales, String personaId) {
    log.debug("update(DatosPersonales datosPersonales, String personaId) - start");

    return repository.findByPersonaId(personaId).map(data -> {
      data.setCiudadNacimiento(datosPersonales.getCiudadNacimiento());
      data.setComAutonomaNacimientoRef(datosPersonales.getComAutonomaNacimientoRef());
      data.setFechaNacimiento(datosPersonales.getFechaNacimiento());
      data.setPaisNacimientoRef(datosPersonales.getPaisNacimientoRef());

      DatosPersonales datosPersonalesUpdated = repository.save(data);

      log.debug("update(DatosPersonales datosPersonales, String personaId) - end");
      return datosPersonalesUpdated;
    }).orElseThrow(() -> new PersonaNotFoundException(personaId));
  }

  /**
   * Obtiene una entidad {@link DatosPersonales} con el id {@link Persona}
   * indicado.
   * 
   * @param id Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosPersonales}.
   */
  public DatosPersonales findByPersonaId(String id) {
    log.debug("findByPersonaId(String id) - start");
    final DatosPersonales returnValue = repository.findByPersonaId(id).orElse(null);
    log.debug("findByPersonaId(String id) - end");
    return returnValue;
  }

  /**
   * Comprueba si existen {@link DatosPersonales} para la {@link Persona}
   * 
   * @param id Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosPersonales} o
   *         <code>false</code> si no los tiene
   */
  public boolean existsByPersonaId(String id) {
    log.debug("existsByPersonaId(String id) - start");
    final boolean returnValue = repository.existsByPersonaId(id);
    log.debug("existsByPersonaId(String id) - end");
    return returnValue;
  }

}
