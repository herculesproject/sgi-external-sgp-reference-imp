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
public class VinculacionOutput implements Serializable {
  private String areaConocimientoRef;
  private boolean personalPropio;
  private String entidadPropiaRef;
  private String empresaRef;
  private String centroRef;
  private CategoriaProfesionalOutput categoriaProfesional;
  private LocalDate fechaObtencionCategoria;
  private String departamentoRef;
}
