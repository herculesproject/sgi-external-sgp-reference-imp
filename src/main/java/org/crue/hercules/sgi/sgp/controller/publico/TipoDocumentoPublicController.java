package org.crue.hercules.sgi.sgp.controller.publico;

import org.crue.hercules.sgi.sgp.converter.TipoDocumentoConverter;
import org.crue.hercules.sgi.sgp.dto.TipoDocumentoOutput;
import org.crue.hercules.sgi.sgp.model.TipoDocumento;
import org.crue.hercules.sgi.sgp.service.TipoDocumentoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TipoDocumentoPublicController
 */
@RestController
@RequestMapping(TipoDocumentoPublicController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class TipoDocumentoPublicController {

  public static final String PATH_DELIMITER = "/";
  public static final String PATH_PUBLIC = PATH_DELIMITER + "public";
  public static final String REQUEST_MAPPING = PATH_PUBLIC + PATH_DELIMITER + "tipos-documento";

  private final TipoDocumentoService service;
  private final TipoDocumentoConverter converter;

  /**
   * Devuelve la lista de {@link TipoDocumento} ordenados alfabéticamente.
   * 
   * @return el listado de entidades {@link TipoDocumento} ordenados.
   */
  @GetMapping()
  public ResponseEntity<Page<TipoDocumentoOutput>> findAll() {
    log.debug("findAll() - start");
    Page<TipoDocumentoOutput> page = converter.convert(service.findAllSorted());
    log.debug("findAll() - end");
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

}
