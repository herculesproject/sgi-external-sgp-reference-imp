package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.DatosAcademicosConverter;
import org.crue.hercules.sgi.sgp.dto.DatosAcademicosOutput;
import org.crue.hercules.sgi.sgp.model.DatosAcademicos;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.service.DatosAcademicosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DatosAcademicosPublicController
 */
@RestController
@RequestMapping(DatosAcademicosPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DatosAcademicosPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "datos-academicos";

  public static final String PATH_PERSONA_ID = PATH_DELIMITER + "persona/{id}";

  private final DatosAcademicosService service;
  private final DatosAcademicosConverter converter;

  /**
   * Devuelve los {@link DatosAcademicos} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link DatosAcademicos} correspondiente al id {@link Persona}.
   */
  @GetMapping(PATH_PERSONA_ID)
  public ResponseEntity<DatosAcademicosOutput> findByPersonaId(@PathVariable String id) {
    log.debug("findByPersonaId({}) - start", id);
    DatosAcademicosOutput returnValue = converter.convert(service.findByPersonaId(id));
    log.debug("findByPersonaId({}) - end", id);
    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
