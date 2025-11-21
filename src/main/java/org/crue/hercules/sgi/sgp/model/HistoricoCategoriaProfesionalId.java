package org.crue.hercules.sgi.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoCategoriaProfesionalId implements Serializable {

  /** Persona Id */
  private String personaId;

  /** Categoria profesional */
  private String categoriaProfesional;

  /** Fecha obtencion categoria */
  private LocalDate fechaObtencion;

}
