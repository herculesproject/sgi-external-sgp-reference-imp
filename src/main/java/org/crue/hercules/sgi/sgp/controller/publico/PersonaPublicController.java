package org.crue.hercules.sgi.sgp.controller.publico;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.crue.hercules.sgi.framework.web.bind.annotation.RequestPageable;
import org.crue.hercules.sgi.sgp.converter.PersonaConverter;
import org.crue.hercules.sgi.sgp.converter.PersonaFormlyConverter;
import org.crue.hercules.sgi.sgp.dto.PersonaFormlyOutput;
import org.crue.hercules.sgi.sgp.dto.PersonaOutput;
import org.crue.hercules.sgi.sgp.enums.TipoColectivo;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.service.PersonaFormlyService;
import org.crue.hercules.sgi.sgp.service.PersonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * PersonaPublicController
 */
@RestController
@RequestMapping(PersonaPublicController.REQUEST_MAPPING)
@Slf4j
@RequiredArgsConstructor
public class PersonaPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "personas";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  public static final String PATH_AUTOCOMPLETE = PATH_DELIMITER + "autocomplete";
  public static final String PATH_FORMLY = PATH_DELIMITER + "formly";
  public static final String PATH_FORMLY_ID = PATH_FORMLY + PATH_ID;

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
   * @throws UnsupportedEncodingException If character encoding needs to be
   *                                      consulted, but
   *                                      named character encoding is not
   *                                      supported
   */
  @GetMapping()
  public ResponseEntity<Page<PersonaOutput>> findAll(@RequestParam(name = "q", required = false) String query,
      @RequestPageable(sort = "s") Pageable paging) throws UnsupportedEncodingException {
    String queryDecoded = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
    log.debug("findAll({}, {}) - start", queryDecoded, paging);
    Page<PersonaOutput> page = converter.convert(service.findAll(queryDecoded, paging));
    log.debug("findAll({}, {}) - end", queryDecoded, paging);
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
   * @throws UnsupportedEncodingException If character encoding needs to be
   *                                      consulted, but
   *                                      named character encoding is not
   *                                      supported
   */
  @GetMapping(PATH_AUTOCOMPLETE)
  public ResponseEntity<Page<PersonaOutput>> findAllAutocomplete(
      @RequestParam(name = "busqueda", required = false) String busqueda,
      @RequestParam(name = "colectivoId", required = false) String colectivoIds,
      @RequestParam(name = "tipoColectivo", required = false) String tipoColectivo)
      throws UnsupportedEncodingException {
    String busquedaDecoded = URLDecoder.decode(busqueda, StandardCharsets.UTF_8.name());
    String colectivoIdsDecoded = colectivoIds != null ? URLDecoder.decode(colectivoIds, StandardCharsets.UTF_8.name())
        : null;
    String tipoColectivoDecoded = tipoColectivo != null
        ? URLDecoder.decode(tipoColectivo, StandardCharsets.UTF_8.name())
        : null;
    log.debug("findAllAutocomplete({}, {}, {}) - start", busquedaDecoded, colectivoIdsDecoded, tipoColectivoDecoded);
    Page<PersonaOutput> page = converter
        .convert(service.findAllAutocomplete(busquedaDecoded, colectivoIds, tipoColectivoDecoded));
    log.debug("findAllAutocomplete({}, {}, {}) - end", busquedaDecoded, colectivoIdsDecoded, tipoColectivoDecoded);
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
