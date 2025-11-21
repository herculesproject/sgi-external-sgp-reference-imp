package org.crue.hercules.sgi.sgp.controller;

import org.crue.hercules.sgi.sgp.converter.HistoricoCategoriaProfesionalConverter;
import org.crue.hercules.sgi.sgp.converter.VinculacionConverter;
import org.crue.hercules.sgi.sgp.dto.HistoricoCategoriaProfesionalOutput;
import org.crue.hercules.sgi.sgp.dto.VinculacionOutput;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.crue.hercules.sgi.sgp.service.HistoricoCategoriaProfesionalService;
import org.crue.hercules.sgi.sgp.service.VinculacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * VinculacionController
 */
@RestController
@RequestMapping(VinculacionController.REQUEST_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class VinculacionController {

  public static final String PATH_DELIMITER = "/";
  public static final String REQUEST_MAPPING = PATH_DELIMITER + "vinculaciones";

  public static final String PATH_PERSONA_ID = PATH_DELIMITER + "persona/{id}";
  public static final String PATH_HISTORICO_CATEGORIAS_PROFESIONALES = PATH_PERSONA_ID + PATH_DELIMITER
      + "vinculaciones-categorias-profesionales";

  private final VinculacionService service;
  private final HistoricoCategoriaProfesionalService historicoCategoriaProfesionalService;
  private final VinculacionConverter converter;
  private final HistoricoCategoriaProfesionalConverter historicoCategoriaProfesionalConverter;

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

  /**
   * Devuelve la {@link HistoricoCategoriaProfesionalOutput} con el id
   * {@link Persona} indicado.
   * 
   * @param id    Identificador de {@link Persona}.
   * @param query filtro de búsqueda.
   * @return {@link Vinculacion} correspondiente al id {@link Persona}.
   */
  @GetMapping(PATH_HISTORICO_CATEGORIAS_PROFESIONALES)
  public ResponseEntity<HistoricoCategoriaProfesionalOutput> findHistoricoCategoriaProfesional(
      @PathVariable String id, @RequestParam(name = "q", required = false) String query) {
    log.debug("findHistoricoCategoriaProfesional({}, {}) - start", id, query);
    HistoricoCategoriaProfesionalOutput returnValue = historicoCategoriaProfesionalConverter
        .convert(historicoCategoriaProfesionalService.findByPersonaIdAndQuery(id, query));
    log.debug("findHistoricoCategoriaProfesional({}, {}) - end", id, query);
    return returnValue == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
        : new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

}
