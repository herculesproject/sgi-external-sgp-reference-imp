package org.crue.hercules.sgi.sgp.repository;

import java.util.Optional;

import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.crue.hercules.sgi.sgp.model.Vinculacion_;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VinculacionRepository
    extends JpaRepository<Vinculacion, String>, JpaSpecificationExecutor<Vinculacion> {

  /**
   * Obtiene una entidad {@link Vinculacion} con el id {@link Persona} indicado.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @return la entidad {@link Vinculacion}.
   */
  @EntityGraph(attributePaths = { Vinculacion_.CATEGORIA_PROFESIONAL }, type = EntityGraph.EntityGraphType.FETCH)
  Optional<Vinculacion> findByPersonaId(String personaId);

  /**
   * Comprueba si existe {@link Vinculacion} para la {@link Persona}
   * 
   * @param personaId Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link Vinculacion} o
   *         <code>false</code> si no los tiene
   */
  boolean existsByPersonaId(String personaId);

}
