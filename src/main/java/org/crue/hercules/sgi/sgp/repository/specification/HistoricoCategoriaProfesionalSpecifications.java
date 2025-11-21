package org.crue.hercules.sgi.sgp.repository.specification;

import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional_;
import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricoCategoriaProfesionalSpecifications {
  /**
   * {@link HistoricoCategoriaProfesional} de la {@link Persona} con el id
   * indicado.
   * 
   * @param personaId Identificador de la {@link Persona}
   * @return specification para obtener los
   *         {@link HistoricoCategoriaProfesional} de la {@link Persona}.
   */
  public static Specification<HistoricoCategoriaProfesional> byPersonaId(String personaId) {
    return (root, query, cb) -> cb.equal(root.get(HistoricoCategoriaProfesional_.personaId), personaId);
  }

}
