package org.crue.hercules.sgi.sgp.controller;

import org.crue.hercules.sgi.sgp.converter.ColectivoConverter;
import org.crue.hercules.sgi.sgp.dto.ColectivoOutput;
import org.crue.hercules.sgi.sgp.model.Colectivo;
import org.crue.hercules.sgi.sgp.service.ColectivoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ColectivoController
 */
@RestController
@RequestMapping(ColectivoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class ColectivoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "colectivos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  /** Colectivo service */
  private final ColectivoService service;
  private final ColectivoConverter converter;

  /**
   * Devuelve una lista de {@link Colectivo} ordenadas alfabéticamente.
   * 
   * @return el listado de entidades {@link Colectivo} ordenadas alfabéticamente.
   */
  @GetMapping()
  public ResponseEntity<Page<ColectivoOutput>> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<ColectivoOutput> page = converter.convert(service.findAllSorted());
    log.debug("findAllSorted() - end");
    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  /**
   * Devuelve la {@link Colectivo} con el id indicado.
   * 
   * @param id Identificador de {@link Colectivo}.
   * @return {@link Colectivo} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public ColectivoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    ColectivoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
