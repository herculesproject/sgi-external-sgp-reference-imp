package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.SexoConverter;
import org.crue.hercules.sgi.sgp.dto.SexoOutput;
import org.crue.hercules.sgi.sgp.model.Sexo;
import org.crue.hercules.sgi.sgp.service.SexoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SexoPublicController
 */
@RestController
@RequestMapping(SexoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class SexoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "sexos";

  private final SexoService service;
  private final SexoConverter converter;

  /**
   * Devuelve la lista de {@link Sexo} ordenados alfabéticamente.
   * 
   * @return el listado de entidades {@link Sexo} ordenados.
   */
  @GetMapping()
  public ResponseEntity<Page<SexoOutput>> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<SexoOutput> page = converter.convert(service.findAllSorted());
    log.debug("findAllSorted() - end");
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

}
