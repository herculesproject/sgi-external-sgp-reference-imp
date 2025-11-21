package org.crue.hercules.sgi.sgp.service;

import java.util.Objects;

import org.crue.hercules.sgi.sgp.exceptions.PersonaNotFoundException;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.crue.hercules.sgi.sgp.repository.HistoricoCategoriaProfesionalRepository;
import org.crue.hercules.sgi.sgp.repository.VinculacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VinculacionService {

  private final VinculacionRepository repository;
  private final HistoricoCategoriaProfesionalRepository historicoCategoriaProfesionalRepository;

  /**
   * Crea la {@link Vinculacion} de una {@link Persona}
   * 
   * @param personaId   Identificador de la {@link Persona}
   * @param vinculacion {@link Vinculacion} de la {@link Persona}
   * @return la {@link Vinculacion}
   */
  @Transactional
  public Vinculacion create(Vinculacion vinculacion, String personaId) {
    log.debug("create(Vinculacion vinculacion, String personaId) - start");
    vinculacion.setPersonaId(personaId);
    Vinculacion vinculacionCreated = repository.save(vinculacion);
    log.debug("create(Vinculacion vinculacion, String personaId) - end");
    return vinculacionCreated;
  }

  /**
   * Actualiza la {@link Vinculacion} de la {@link Persona}
   * 
   * @param vinculacion {@link Vinculacion} de la persona actualizada
   * @param personaId   Identificador de la {@link Persona}
   * @return la {@link Vinculacion} de la {@link Persona} actualizada
   */
  @Transactional
  public Vinculacion update(Vinculacion vinculacion, String personaId) {
    log.debug("update(Vinculacion vinculacion, String personaId) - start");

    return repository.findByPersonaId(personaId).map(data -> {
      if (!Objects.equals(data.getCategoriaProfesional(), vinculacion.getCategoriaProfesional())
          && !Objects.equals(data.getFechaObtencionCategoria(), vinculacion.getFechaObtencionCategoria())) {

        HistoricoCategoriaProfesional categoriaProfesional = null;

        if (data.getFechaObtencionCategoria() == null || vinculacion.getFechaObtencionCategoria() == null
            || vinculacion.getFechaObtencionCategoria().isAfter(data.getFechaObtencionCategoria())) {
          categoriaProfesional = HistoricoCategoriaProfesional.builder()
              .personaId(personaId)
              .categoriaProfesional(data.getCategoriaProfesional())
              .fechaObtencion(data.getFechaObtencionCategoria())
              .build();
        } else {
          categoriaProfesional = HistoricoCategoriaProfesional.builder()
              .personaId(personaId)
              .categoriaProfesional(vinculacion.getCategoriaProfesional())
              .fechaObtencion(vinculacion.getFechaObtencionCategoria())
              .build();

          vinculacion.setCategoriaProfesional(data.getCategoriaProfesional());
          vinculacion.setFechaObtencionCategoria(data.getFechaObtencionCategoria());
        }

        historicoCategoriaProfesionalRepository.save(categoriaProfesional);
      }

      data.setAreaConocimientoRef(vinculacion.getAreaConocimientoRef());
      data.setCategoriaProfesional(vinculacion.getCategoriaProfesional());
      data.setCentroRef(vinculacion.getCentroRef());
      data.setDepartamentoRef(vinculacion.getDepartamentoRef());
      data.setEmpresaRef(vinculacion.getEmpresaRef());
      data.setEntidadPropiaRef(vinculacion.getEntidadPropiaRef());
      data.setFechaObtencionCategoria(vinculacion.getFechaObtencionCategoria());
      data.setPersonalPropio(vinculacion.getPersonalPropio());

      Vinculacion vinculacionUpdated = repository.save(data);

      log.debug("update(Vinculacion vinculacion, String personaId) - end");
      return vinculacionUpdated;
    }).orElseThrow(() -> new PersonaNotFoundException(personaId));
  }

  /**
   * Obtiene una entidad {@link Vinculacion} con el id {@link Persona} indicado.
   * 
   * @param id Identificador de la entidad {@link Persona}.
   * @return la entidad {@link Vinculacion}.
   */
  public Vinculacion findByPersonaId(String id) {
    log.debug("findByPersonaId(String id) - start");
    final Vinculacion returnValue = repository.findByPersonaId(id).orElse(null);
    log.debug("findByPersonaId(String id) - end");
    return returnValue;
  }

  /**
   * Comprueba si existe {@link Vinculacion} para la {@link Persona}
   * 
   * @param id Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link Vinculacion} o
   *         <code>false</code> si no la tiene
   */
  public boolean existsByPersonaId(String id) {
    log.debug("existsByPersonaId(String id) - start");
    final boolean returnValue = repository.existsByPersonaId(id);
    log.debug("existsByPersonaId(String id) - end");
    return returnValue;
  }

}
