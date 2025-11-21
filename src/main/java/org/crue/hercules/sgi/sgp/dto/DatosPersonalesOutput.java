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
public class DatosPersonalesOutput implements Serializable {
  private LocalDate fechaNacimiento;
  private String paisNacimientoRef;
  private String comAutonomaNacimientoRef;
  private String ciudadNacimiento;
}
