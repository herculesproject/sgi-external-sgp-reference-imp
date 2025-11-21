package org.crue.hercules.sgi.sgp.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class PersonaFormlyInput implements Serializable {

  // Datos generales
  private String personaId;

  @NotBlank
  private String apellidos;

  @NotBlank
  private String colectivoId;

  private String entidadRef;

  @NotBlank
  private String nombre;

  @NotBlank
  private String numeroDocumento;

  @NotNull
  private boolean personalPropio;

  @NotBlank
  private String sexoId;

  @NotBlank
  private String tipoDocumentoId;

  // Datos personales
  private String ciudadNacimiento;
  private String comAutonomaNacimientoRef;
  private LocalDate fechaNacimiento;
  private String paisNacimientoRef;

  // Datos academicos
  private LocalDate fechaObtencion;
  private String nivelAcademicoId;

  // Datos contacto
  private String ciudadContacto;
  private String codigoPostalContacto;
  private String comAutonomaContactoRef;
  private String direccionContacto;

  @NotEmpty
  private List<String> emails;

  private List<String> moviles;
  private String paisContactoRef;
  private String provinciaContactoRef;
  private List<String> telefonos;

  // vinculacion
  private String areaConocimientoRef;
  private String categoriaId;
  private String centroRef;
  private String departamentoId;
  private LocalDate fechaCategoria;

}
