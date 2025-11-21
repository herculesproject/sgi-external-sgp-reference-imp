package org.crue.hercules.sgi.sgp.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoCategoriaProfesionalOutput implements Serializable {
  private CategoriaProfesionalOutput categoriaProfesional;
  private LocalDate fechaObtencion;
}
