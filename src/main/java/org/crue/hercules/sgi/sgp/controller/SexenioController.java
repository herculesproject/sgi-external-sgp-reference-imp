package org.crue.hercules.sgi.sgp.controller;

import java.time.Instant;

import org.apache.commons.lang3.NotImplementedException;
import org.crue.hercules.sgi.sgp.dto.SexenioOutput;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SexenioController
 */
@RestController
@RequestMapping(SexenioController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class SexenioController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "sexenios";

  /**
   * Devuelve la de {@link SexenioOutput} de cada persona en la fecha indicada.
   * 
   * @param fecha fecha para la que se hace la busqueda
   * @return el listado de {@link SexenioOutput} de cada persona en la fecha
   *         indicada.
   */
  @GetMapping()
  public ResponseEntity<Page<SexenioOutput>> findAll(@RequestParam(name = "fecha", required = true) Instant fecha) {
    log.debug("findAll({}) - start", fecha);
    log.debug("findAll({}) - end", fecha);
    // TODO: Not Implemented
    throw new NotImplementedException();
  }

}
