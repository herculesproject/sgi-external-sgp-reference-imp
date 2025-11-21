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
public class PersonaOutput implements Serializable {
  private String id;
  private String nombre;
  private String apellidos;
  private SexoOutput sexo;
  private String numeroDocumento;
  private TipoDocumentoOutput tipoDocumento;
  private String empresaRef;
  private boolean personalPropio;
  private String entidadPropiaRef;
  private List<EmailOutput> emails;
  private boolean activo;
}
