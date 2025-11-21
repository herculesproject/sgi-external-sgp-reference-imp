package org.crue.hercules.sgi.sgp.controller;

import javax.validation.Valid;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sgp.converter.PersonaConverter;
import org.crue.hercules.sgi.sgp.converter.PersonaFormlyConverter;
import org.crue.hercules.sgi.sgp.dto.PersonaFormlyInput;
import org.crue.hercules.sgi.sgp.dto.PersonaFormlyOutput;
import org.crue.hercules.sgi.sgp.dto.PersonaOutput;
import org.crue.hercules.sgi.sgp.enums.TipoColectivo;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.service.PersonaFormlyService;
import org.crue.hercules.sgi.sgp.service.PersonaService;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PersonaController
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class PersonaController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PERSONAS = PATH_DELIMITER + "personas";

  public static final String PATH_ID = PATH_PERSONAS + PATH_DELIMITER + "{id}";
  public static final String PATH_FOTOGRAFIA = PATH_PERSONAS + PATH_ID + PATH_DELIMITER + "fotografia";

  public static final String PATH_AUTOCOMPLETE = PATH_PERSONAS + "Fast";
  public static final String PATH_FORMLY = PATH_PERSONAS + PATH_DELIMITER + "formly";
  public static final String PATH_FORMLY_ID = PATH_PERSONAS + PATH_FORMLY + PATH_ID;

  private final PersonaService service;
  private final PersonaFormlyService personaFormlyService;
  private final PersonaConverter converter;
  private final PersonaFormlyConverter converterPersonaFormly;

  /**
   * Devuelve una lista paginada y filtrada de {@link Persona}.
   * 
   * @param query  filtro de búsqueda.
   * @param paging {@link Pageable}.
   * @return el listado de entidades {@link Persona} paginadas y filtradas.
   */
  @GetMapping(PATH_PERSONAS)
  public ResponseEntity<Page<PersonaOutput>> findAll(@RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) {
    log.debug("findAll({}, {}) - start", query, paging);
    Page<PersonaOutput> page = converter.convert(service.findAll(query, paging));
    log.debug("findAll({}, {}) - end", query, paging);
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

  /**
   * Devuelve las primeras {@link PersonaService#AUTOCOMPLETE_PAGE_SIZE}
   * entidades {@link Persona} paginadas y filtradas.
   * 
   * @param busqueda      texto para buscar en el nombre, apellidos y emails
   * @param colectivoIds  lista de indentificadores de colectivos separados por
   *                      ","
   * @param tipoColectivo {@link TipoColectivo}
   * @return el listado de entidades {@link Persona} paginadas y filtradas.
   */
  @GetMapping(PATH_AUTOCOMPLETE)
  public ResponseEntity<Page<PersonaOutput>> findAllAutocomplete(
      @RequestParam(name = "busqueda", required = false) String busqueda,
      @RequestParam(name = "colectivoId", required = false) String colectivoIds,
      @RequestParam(name = "tipoColectivo", required = false) String tipoColectivo) {
    log.debug("findAllAutocomplete({}, {}, {}) - start", busqueda, colectivoIds, tipoColectivo);
    Page<PersonaOutput> page = converter
        .convert(service.findAllAutocomplete(busqueda, colectivoIds, tipoColectivo));
    log.debug("findAllAutocomplete({}, {}, {}) - end", busqueda, colectivoIds, tipoColectivo);
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

  /**
   * Devuelve la {@link Persona} con el id indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link Persona} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public PersonaOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    PersonaOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

  /**
   * Devuelve la fotografia de la {@link Persona} con el id indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link Object}.
   */
  @GetMapping(PATH_FOTOGRAFIA)
  public Object findFotografiaByPersonaId(@PathVariable Long id) {
    // TODO: Not Implemented
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * Crea una nueva {@link Persona}
   * 
   * @param persona la nueva {@link Persona}.
   * @return {@link Persona} creada.
   */
  @PostMapping(PATH_FORMLY)
  public ResponseEntity<String> createPersonaFormly(@Valid @RequestBody PersonaFormlyInput persona) {
    log.debug("createPersonaFormly(PersonaFormlyInput persona) - start");
    String personaId = personaFormlyService.create(converterPersonaFormly.convert(persona));
    log.debug("createPersonaFormly(PersonaFormlyInput persona) - end");
    return new ResponseEntity<>(JSONObject.quote(personaId), HttpStatus.OK);
  }

  /**
   * Actualiza una nueva {@link Persona}
   * 
   * @param persona la {@link Persona} actualizada.
   * @param id      identificador de la {@link Persona}.
   * @return {@link Persona} actualizada.
   */
  @PutMapping(PATH_FORMLY_ID)
  public ResponseEntity<String> updatePersonaFormly(@Valid @RequestBody PersonaFormlyInput persona,
      @PathVariable String id) {
    log.debug("updatePersonaFormly(PersonaFormlyInput persona, String id) - start");
    String personaId = personaFormlyService.update(converterPersonaFormly.convert(persona), id);
    log.debug("updatePersonaFormly(PersonaFormlyInput persona, String id) - end");
    return new ResponseEntity<>(JSONObject.quote(personaId), HttpStatus.OK);
  }

  /**
   * Devuelve la {@link Persona} con el id indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link Persona} correspondiente al id
   */
  @GetMapping(PATH_FORMLY_ID)
  public PersonaFormlyOutput findPersonaFormlyById(@PathVariable String id) {
    log.debug("findPersonaFormlyById({}) - start", id);
    PersonaFormlyOutput returnValue = converterPersonaFormly.convert(personaFormlyService.findById(id));
    log.debug("findPersonaFormlyById({}) - end", id);
    return returnValue;
  }

}
