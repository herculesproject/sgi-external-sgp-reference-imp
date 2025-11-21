package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.VinculacionConverter;
import org.crue.hercules.sgi.sgp.dto.VinculacionOutput;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.crue.hercules.sgi.sgp.service.VinculacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * VinculacionPublicController
 */
@RestController
@RequestMapping(VinculacionPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class VinculacionPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "vinculaciones";

  public static final String PATH_PERSONA_ID = PATH_DELIMITER + "persona/{id}";

  private final VinculacionService service;
  private final VinculacionConverter converter;

  /**
   * Devuelve el {@link Vinculacion} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de {@link Persona}.
   * @return {@link Vinculacion} correspondiente al id {@link Persona}.
   */
  @GetMapping(PATH_PERSONA_ID)
  public ResponseEntity<VinculacionOutput> findByPersonaId(@PathVariable String id) {
    log.debug("findByPersonaId({}) - start", id);
    VinculacionOutput returnValue = converter.convert(service.findByPersonaId(id));
    log.debug("findByPersonaId({}) - end", id);
    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
