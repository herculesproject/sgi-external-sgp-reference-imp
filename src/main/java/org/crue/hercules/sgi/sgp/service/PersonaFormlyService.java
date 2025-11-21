package org.crue.hercules.sgi.sgp.service;

import java.util.List;

import org.crue.hercules.sgi.sgp.model.DatosAcademicos;
import org.crue.hercules.sgi.sgp.model.DatosContacto;
import org.crue.hercules.sgi.sgp.model.DatosPersonales;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.PersonaFormlyRequest;
import org.crue.hercules.sgi.sgp.model.PersonaFormlyResponse;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.crue.hercules.sgi.sgp.service.sgi.SgiCnfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaFormlyService {

  private final PersonaService personaService;
  private final DatosAcademicosService datosAcademicosService;
  private final DatosContactoService datosContactoService;
  private final DatosPersonalesService datosPersonalesService;
  private final VinculacionService vinculacionService;
  private final HistoricoCategoriaProfesionalService historicoCategoriaProfesionalService;
  private final SgiCnfService sgiCnfService;

  /**
   * Obtiene una entidad {@link PersonaFormlyResponse} por id.
   * 
   * @param id identificador de la entidad {@link Persona}.
   * @return la entidad {@link PersonaFormlyResponse}.
   */
  public PersonaFormlyResponse findById(String id) {
    log.debug("findById(String id) - start");
    final Persona persona = personaService.findById(id);
    DatosAcademicos datosAcademicos = datosAcademicosService.findByPersonaId(id);
    DatosContacto datosContacto = datosContactoService.findByPersonaId(id);
    DatosPersonales datosPersonales = datosPersonalesService.findByPersonaId(id);
    Vinculacion vinculacion = vinculacionService.findByPersonaId(id);
    List<HistoricoCategoriaProfesional> categoriasProfesionalesHistorico = historicoCategoriaProfesionalService
        .findByPersonaId(id);

    PersonaFormlyResponse returnValue = new PersonaFormlyResponse(persona, datosAcademicos, datosContacto,
        datosPersonales, vinculacion, categoriasProfesionalesHistorico);
    log.debug("findById(String id) - end");
    return returnValue;
  }

  @Transactional
  public String create(PersonaFormlyRequest personaFormly) {
    log.debug("create(PersonaFormlyRequest personaFormly) - start");
    Persona personaNew = personaFormly.getPersona();
    if (personaNew.isPersonalPropio()) {
      personaNew.setEntidadPropiaRef(sgiCnfService.getEntidadPropiaRef());
    }

    Persona personaCreated = personaService.create(personaNew);

    if (personaFormly.getDatosAcademicos() != null) {
      datosAcademicosService.create(personaFormly.getDatosAcademicos(), personaCreated.getId());
    }

    datosContactoService.create(personaFormly.getDatosContacto(), personaCreated.getId());

    if (personaFormly.getDatosPersonales() != null) {
      datosPersonalesService.create(personaFormly.getDatosPersonales(), personaCreated.getId());
    }

    vinculacionService.create(personaFormly.getVinculacion(), personaCreated.getId());

    log.debug("create(PersonaFormly personaFormly) - end");
    return personaCreated.getId();
  }

  @Transactional
  public String update(PersonaFormlyRequest personaFormly, String personaId) {
    log.debug("update(PersonaFormlyRequest personaFormly) - start");
    Persona personaUpdated = personaFormly.getPersona();
    Vinculacion vinculacionUpdated = personaFormly.getVinculacion();
    if (personaUpdated.isPersonalPropio()) {
      personaUpdated.setEntidadPropiaRef(sgiCnfService.getEntidadPropiaRef());
      vinculacionUpdated.setEntidadPropiaRef(sgiCnfService.getEntidadPropiaRef());
    } else {
      personaUpdated.setEntidadPropiaRef(null);
      vinculacionUpdated.setEntidadPropiaRef(null);
    }

    personaService.update(personaUpdated);

    saveOrUpdateDatosAcademicos(personaFormly.getDatosAcademicos(), personaId);
    saveOrUpdateDatosContacto(personaFormly.getDatosContacto(), personaId);
    saveOrUpdateDatosPersonales(personaFormly.getDatosPersonales(), personaId);
    saveOrUpdateVinculacion(vinculacionUpdated, personaId);
    log.debug("update(PersonaFormlyRequest personaFormly) - end");
    return personaId;
  }

  /**
   * Actualiza los {@link DatosAcademicos} de la {@link Persona} o los crea si no
   * existen
   * 
   * @param datosAcademicos {@link DatosAcademicos} actualizados
   * @param personaId       Identificador de la {@link Persona}
   */
  private void saveOrUpdateDatosAcademicos(DatosAcademicos datosAcademicos, String personaId) {
    log.debug("saveOrUpdateDatosAcademicos(DatosAcademicos datosAcademicos, String personaId) - start");
    if (datosAcademicosService.existsByPersonaId(personaId)) {
      datosAcademicosService.update(datosAcademicos, personaId);
    } else {
      datosAcademicosService.create(datosAcademicos, personaId);
    }
    log.debug("saveOrUpdateDatosAcademicos(DatosAcademicos datosAcademicos, String personaId) - ends");
  }

  /**
   * Actualiza los {@link DatosContacto} de la {@link Persona} o los crea si no
   * existen
   * 
   * @param datosContacto {@link DatosContacto} actualizados
   * @param personaId     Identificador de la {@link Persona}
   */
  private void saveOrUpdateDatosContacto(DatosContacto datosContacto, String personaId) {
    log.debug("saveOrUpdateDatosContacto(DatosContacto datosContacto, String personaId) - start");
    if (datosContactoService.existsByPersonaId(personaId)) {
      datosContactoService.update(datosContacto, personaId);
    } else {
      datosContactoService.create(datosContacto, personaId);
    }
    log.debug("saveOrUpdateDatosContacto(DatosContacto datosContacto, String personaId) - ends");
  }

  /**
   * Actualiza los {@link DatosPersonales} de la {@link Persona} o los crea si no
   * existen
   * 
   * @param datosPersonales {@link DatosPersonales} actualizados
   * @param personaId       Identificador de la {@link Persona}
   */
  private void saveOrUpdateDatosPersonales(DatosPersonales datosPersonales, String personaId) {
    log.debug("saveOrUpdateDatosPersonales(DatosPersonales datosPersonales, String personaId) - start");
    if (datosPersonalesService.existsByPersonaId(personaId)) {
      datosPersonalesService.update(datosPersonales, personaId);
    } else {
      datosPersonalesService.create(datosPersonales, personaId);
    }
    log.debug("saveOrUpdateDatosPersonales(DatosPersonales datosPersonales, String personaId) - ends");
  }

  /**
   * Actualiza la {@link Vinculacion} de la {@link Persona} o la crea si no existe
   * 
   * @param vinculacion {@link Vinculacion} actualizada
   * @param personaId   Identificador de la {@link Persona}
   */
  private void saveOrUpdateVinculacion(Vinculacion vinculacion, String personaId) {
    log.debug("saveOrUpdateVinculacion(Vinculacion vinculacion, String personaId) - start");
    if (vinculacionService.existsByPersonaId(personaId)) {
      vinculacionService.update(vinculacion, personaId);
    } else {
      vinculacionService.create(vinculacion, personaId);
    }
    log.debug("saveOrUpdateVinculacion(Vinculacion vinculacion, String personaId) - ends");
  }

}
