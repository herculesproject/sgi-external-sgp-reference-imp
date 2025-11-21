package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.NivelAcademicoConverter;
import org.crue.hercules.sgi.sgp.dto.NivelAcademicoOutput;
import org.crue.hercules.sgi.sgp.model.NivelAcademico;
import org.crue.hercules.sgi.sgp.service.NivelAcademicoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * NivelAcademicoPublicController
 */
@RestController
@RequestMapping(NivelAcademicoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class NivelAcademicoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "niveles-academicos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final NivelAcademicoService service;
  private final NivelAcademicoConverter converter;

  /**
   * Devuelve la {@link NivelAcademico} con el id indicado.
   * 
   * @param id Identificador de {@link NivelAcademico}.
   * @return {@link NivelAcademico} correspondiente al id
   */
  @GetMapping(PATH_ID)
  public NivelAcademicoOutput findById(@PathVariable String id) {
    log.debug("findById({}) - start", id);
    NivelAcademicoOutput returnValue = converter.convert(service.findById(id));
    log.debug("findById({}) - end", id);
    return returnValue;
  }

}
