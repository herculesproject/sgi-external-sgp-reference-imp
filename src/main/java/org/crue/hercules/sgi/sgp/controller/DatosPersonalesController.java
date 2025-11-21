package org.crue.hercules.sgi.sgp.controller;

import org.crue.hercules.sgi.sgp.converter.DatosPersonalesConverter;
import org.crue.hercules.sgi.sgp.dto.DatosPersonalesOutput;
import org.crue.hercules.sgi.sgp.model.DatosPersonales;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.service.DatosPersonalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DatosPersonalesController
 */
@RestController
@RequestMapping(DatosPersonalesController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DatosPersonalesController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "datos-personales";

  public static final String PATH_PERSONA_ID = PATH_DELIMITER + "persona/{id}";

  private final DatosPersonalesService service;
  private final DatosPersonalesConverter converter;

  /**
   * Devuelve los {@link DatosPersonales} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link DatosPersonales} correspondiente al id {@link Persona}.
   */
  @GetMapping(PATH_PERSONA_ID)
  public ResponseEntity<DatosPersonalesOutput> findByPersonaId(@PathVariable String id) {
    log.debug("findByPersonaId({}) - start", id);
    DatosPersonalesOutput returnValue = converter.convert(service.findByPersonaId(id));
    log.debug("findByPersonaId({}) - end", id);
    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
