package org.crue.hercules.sgi.sgp.service;

import java.util.UUID;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLJPASupport;
import org.crue.hercules.sgi.sgp.enums.TipoColectivo;
import org.crue.hercules.sgi.sgp.exceptions.PersonaNotFoundException;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Persona_;
import org.crue.hercules.sgi.sgp.repository.PersonaRepository;
import org.crue.hercules.sgi.sgp.repository.predicate.PersonaPredicateResolver;
import org.crue.hercules.sgi.sgp.repository.specification.PersonaSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class PersonaService {
  private static final int AUTOCOMPLETE_PAGE = 0;
  private static final int AUTOCOMPLETE_PAGE_SIZE = 11;

  private final PersonaRepository repository;

  public PersonaService(PersonaRepository personaRepository) {
    this.repository = personaRepository;
  }

  /**
   * Crea una persona
   * 
   * @param persona una persona
   * @return la persona creada
   */
  @Transactional
  public Persona create(Persona persona) {
    log.debug("create(Persona persona) - start");
    persona.setId(UUID.randomUUID().toString());
    Persona personaCreated = repository.save(persona);
    log.debug("create(Persona persona) - end");
    return personaCreated;
  }

  /**
   * Actualiza una persona
   * 
   * @param persona una persona
   * @return la persona actualizada
   */
  @Transactional
  public Persona update(Persona persona) {
    log.debug("update(Persona persona) - start");

    return repository.findById(persona.getId()).map(data -> {
      data.setApellidos(persona.getApellidos());
      data.setColectivoId(persona.getColectivoId());
      data.setEmpresaRef(persona.getEmpresaRef());
      data.setEntidadPropiaRef(persona.getEntidadPropiaRef());
      data.setNombre(persona.getNombre());
      data.setNumeroDocumento(persona.getNumeroDocumento());
      data.setPersonalPropio(persona.isPersonalPropio());
      data.setSexo(persona.getSexo());
      data.setTipoDocumento(persona.getTipoDocumento());

      Persona personaUpdated = repository.save(data);
      log.debug("update(Persona persona) - end");
      return personaUpdated;
    }).orElseThrow(() -> new PersonaNotFoundException(persona.getId()));
  }

  /**
   * Obtiene una entidad {@link Persona} por id.
   * 
   * @param id identificador de la entidad {@link Persona}.
   * @return la entidad {@link Persona}.
   */
  public Persona findById(String id) {
    log.debug("findById(String id) - start");
    final Persona returnValue = repository.findById(id).orElseThrow(() -> new PersonaNotFoundException(id));
    log.debug("findById(String id) - end");
    return returnValue;
  }

  /**
   * Obtiene todas las entidades {@link Persona} paginadas y filtradas.
   *
   * @param query  información del filtro.
   * @param paging información de paginación.
   * @return el listado de entidades {@link Persona} paginadas y filtradas.
   */
  public Page<Persona> findAll(String query, Pageable paging) {
    log.debug("findAll(String query, Pageable paging) - start");

    Specification<Persona> specs = SgiRSQLJPASupport.toSpecification(query, PersonaPredicateResolver.getInstance());

    Page<Persona> returnValue = repository.findAll(specs, paging);
    log.debug("findAll(String query, Pageable paging) - end");
    return returnValue;
  }

  /**
   * Obtiene las primeras {@link PersonaService#AUTOCOMPLETE_PAGE_SIZE} entidades
   * {@link Persona} paginadas y filtradas.
   *
   * @param busqueda      texto para buscar en el nombre, apellidos y emails
   * @param colectivoIds  lista de indentificadores de colectivos separados por
   *                      ","
   * @param tipoColectivo {@link TipoColectivo}
   * @return el listado de entidades {@link Persona} paginadas y filtradas.
   */
  public Page<Persona> findAllAutocomplete(String busqueda, String colectivoIds, String tipoColectivo) {
    log.debug(
        "findAllAutocomplete(String busqueda, String colectivoIds, String tipoColectivo) - start");

    Specification<Persona> specs = PersonaSpecifications.byNombreAndApellidosAndEmails(busqueda);

    if (!StringUtils.isEmpty(colectivoIds)) {
      specs = specs.and(PersonaSpecifications.byColectivoIds(colectivoIds));
    } else if (!StringUtils.isEmpty(tipoColectivo)) {
      specs = specs.and(PersonaSpecifications.byTipoColectivo(tipoColectivo));
    }

    Page<Persona> returnValue = repository.findAll(specs, PageRequest.of(AUTOCOMPLETE_PAGE, AUTOCOMPLETE_PAGE_SIZE,
        Sort.by(Sort.Direction.ASC, Persona_.NOMBRE).and(Sort.by(Sort.Direction.ASC, Persona_.APELLIDOS))));
    log.debug("findAllAutocomplete(String busqueda, String colectivoIds, String tipoColectivo) - end");
    return returnValue;
  }

}
