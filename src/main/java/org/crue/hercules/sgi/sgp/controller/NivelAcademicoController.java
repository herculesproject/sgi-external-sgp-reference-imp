package org.crue.hercules.sgi.sgp.controller;

import org.crue.hercules.sgi.sgp.converter.NivelAcademicoConverter;
import org.crue.hercules.sgi.sgp.dto.NivelAcademicoOutput;
import org.crue.hercules.sgi.sgp.model.NivelAcademico;
import org.crue.hercules.sgi.sgp.service.NivelAcademicoService;
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
 * NivelAcademicoController
 */
@RestController
@RequestMapping(NivelAcademicoController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class NivelAcademicoController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "niveles-academicos";

  public static final String PATH_ID = PATH_DELIMITER + "{id}";

  private final NivelAcademicoService service;
  private final NivelAcademicoConverter converter;

  /**
   * Devuelve una lista de {@link NivelAcademico} ordenados alfabéticamente.
   * 
   * @return el listado de entidades {@link NivelAcademico} ordenados
   *         alfabéticamente
   */
  @GetMapping()
  public ResponseEntity<Page<NivelAcademicoOutput>> findAllSorted() {
    log.debug("findAllSorted() - start");
    Page<NivelAcademicoOutput> page = converter.convert(service.findAllSorted());
    log.debug("findAllSorted() - end");
    return page.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(page, HttpStatus.OK);
  }

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
