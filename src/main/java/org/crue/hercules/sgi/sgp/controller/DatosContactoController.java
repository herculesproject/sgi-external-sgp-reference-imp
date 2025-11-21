package org.crue.hercules.sgi.sgp.controller;

import org.crue.hercules.sgi.sgp.converter.DatosContactoConverter;
import org.crue.hercules.sgi.sgp.dto.DatosContactoOutput;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.service.DatosContactoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DatosContactoController
 */
@RestController
@RequestMapping(DatosContactoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DatosContactoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "datos-contacto";

  public static final String PATH_PERSONA_ID = PATH_DELIMITER + "persona/{id}";

  private final DatosContactoService service;
  private final DatosContactoConverter converter;

  /**
   * Devuelve los {@link DatosContactoOutput} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link DatosContactoOutput} correspondiente al id {@link Persona}.
   */
  @GetMapping(PATH_PERSONA_ID)
  public ResponseEntity<DatosContactoOutput> findByPersonaId(@PathVariable String id) {
    log.debug("findByPersonaId({}) - start", id);
    DatosContactoOutput returnValue = converter.convert(service.findByPersonaId(id));
    log.debug("findByPersonaId({}) - end", id);

    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
