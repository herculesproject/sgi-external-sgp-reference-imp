package org.crue.hercules.sgi.sgp.repository;

import java.util.Optional;

import org.crue.hercules.sgi.sgp.model.DatosAcademicos;
import org.crue.hercules.sgi.sgp.model.DatosAcademicos_;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatosAcademicosRepository
    extends JpaRepository<DatosAcademicos, String>, JpaSpecificationExecutor<DatosAcademicos> {

  /**
   * Obtiene una entidad {@link DatosAcademicos} con el id {@link Persona}
   * indicado.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosAcademicos}.
   */
  @EntityGraph(attributePaths = { DatosAcademicos_.NIVEL_ACADEMICO }, type = EntityGraph.EntityGraphType.FETCH)
  Optional<DatosAcademicos> findByPersonaId(String personaId);

  /**
   * Comprueba si existen {@link DatosAcademicos} para la {@link Persona}
   * 
   * @param personaId Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosAcademicos} o
   *         <code>false</code> si no los tiene
   */
  boolean existsByPersonaId(String personaId);

}
