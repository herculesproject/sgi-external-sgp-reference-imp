package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.ColectivoConverter;
import org.crue.hercules.sgi.sgp.dto.ColectivoOutput;
import org.crue.hercules.sgi.sgp.model.Colectivo;
import org.crue.hercules.sgi.sgp.service.ColectivoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ColectivoPublicController
 */
@RestController
@RequestMapping(ColectivoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class ColectivoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "colectivos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final ColectivoService service;
  private final ColectivoConverter converter;

  /**
   * Devuelve el {@link Colectivo} con el id indicado.
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
