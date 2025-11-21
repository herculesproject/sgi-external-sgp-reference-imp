package org.crue.hercules.sgi.sgp.dto;

import java.io.Serializable;
import java.util.List;

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
public class DatosContactoOutput implements Serializable {
  private String paisContactoRef;
  private String comAutonomaContactoRef;
  private String provinciaContactoRef;
  private String ciudadContacto;
  private String codigoPostalContacto;
  private List<EmailOutput> emails;
  private List<String> telefonos;
  private List<String> moviles;
  private String direccionContacto;
}
