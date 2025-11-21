package org.crue.hercules.sgi.sgp.service;

import org.crue.hercules.sgi.sgp.exceptions.PersonaNotFoundException;
import org.crue.hercules.sgi.sgp.model.DatosContacto;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.repository.DatosContactoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DatosContactoService {

  private final DatosContactoRepository repository;

  public DatosContactoService(DatosContactoRepository datosContactoRepository) {
    this.repository = datosContactoRepository;
  }

  /**
   * Crea los datos de contacto de una persona
   * 
   * @param personaId     Identificador de la persona
   * @param datosContacto Datos de contacto de la persona
   * @return los datos de contacto
   */
  @Transactional
  public DatosContacto create(DatosContacto datosContacto, String personaId) {
    log.debug("create(DatosContacto datosContacto, String personaId) - start");
    datosContacto.setPersonaId(personaId);
    DatosContacto datosContactoCreated = repository.save(datosContacto);
    log.debug("create(DatosContacto datosContacto, String personaId) - end");
    return datosContactoCreated;
  }

  /**
   * Actualiza los {@link DatosContacto} de la {@link Persona}
   * 
   * @param datosContacto {@link DatosContacto} de la persona actualizada
   * @param personaId     Identificador de la {@link Persona}
   * @return los {@link DatosContacto} de la {@link Persona} actualizada
   */
  @Transactional
  public DatosContacto update(DatosContacto datosContacto, String personaId) {
    log.debug("update(DatosContacto datosContactoUpdated, String personaId) - start");

    return repository.findByPersonaId(personaId).map(data -> {
      data.setCiudadContacto(datosContacto.getCiudadContacto());
      data.setCodigoPostalContacto(datosContacto.getCodigoPostalContacto());
      data.setComAutonomaContactoRef(datosContacto.getComAutonomaContactoRef());
      data.setDireccionContacto(datosContacto.getDireccionContacto());
      data.setEmails(datosContacto.getEmails());
      data.setMoviles(datosContacto.getMoviles());
      data.setPaisContactoRef(datosContacto.getPaisContactoRef());
      data.setProvinciaContactoRef(datosContacto.getProvinciaContactoRef());
      data.setTelefonos(datosContacto.getTelefonos());

      DatosContacto datosContactoUpdated = repository.save(data);

      log.debug("update(DatosContacto datosContactoUpdated, String personaId) - end");
      return datosContactoUpdated;
    }).orElseThrow(() -> new PersonaNotFoundException(personaId));
  }

  /**
   * Obtiene una entidad {@link DatosContacto} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosContacto}.
   */
  public DatosContacto findByPersonaId(String id) {
    log.debug("findByPersonaId(String id) - start");
    final DatosContacto returnValue = repository.findByPersonaId(id).orElse(null);
    log.debug("findByPersonaId(String id) - end");
    return returnValue;
  }

  /**
   * Comprueba si existen {@link DatosContacto} para la {@link Persona}
   * 
   * @param id Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosContacto} o
   *         <code>false</code> si no los tiene
   */
  public boolean existsByPersonaId(String id) {
    log.debug("existsByPersonaId(String id) - start");
    final boolean returnValue = repository.existsByPersonaId(id);
    log.debug("existsByPersonaId(String id) - end");
    return returnValue;
  }

}
