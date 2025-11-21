package org.crue.hercules.sgi.sgp.repository;

import java.util.Optional;

import org.crue.hercules.sgi.sgp.model.DatosContacto;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatosContactoRepository
    extends JpaRepository<DatosContacto, String>, JpaSpecificationExecutor<DatosContacto> {

  /**
   * Obtiene una entidad {@link DatosContacto} con el id {@link Persona} indicado.
   * 
   * @param personaId Identificador de la entidad {@link Persona}.
   * @return la entidad {@link DatosContacto}.
   */
  Optional<DatosContacto> findByPersonaId(String personaId);

  /**
   * Comprueba si existen {@link DatosContacto} para la {@link Persona}
   * 
   * @param personaId Identificador de la {@link Persona}
   * @return <code>true</code> si tiene {@link DatosContacto} o
   *         <code>false</code> si no los tiene
   */
  boolean existsByPersonaId(String personaId);

}
