package org.crue.hercules.sgi.sgp.repository;

import java.util.Optional;

import org.crue.hercules.sgi.sgp.model.DatosPersonales;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatosPersonalesRepository
    extends JpaRepository<DatosPersonales, String>, JpaSpecificationExecutor<DatosPersonales> {

  /**
   * Obtiene una entidad {@link DatosPersonales} con el id {@link Persona}
   * indicado.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosPersonales}.
   */
  Optional<DatosPersonales> findByPersonaId(String personaId);

  /**
   * Comprueba si existen {@link DatosPersonales} para la {@link Persona}
   * 
   * @param personaId Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosPersonales} o
   *         <code>false</code> si no los tiene
   */
  boolean existsByPersonaId(String personaId);

}
