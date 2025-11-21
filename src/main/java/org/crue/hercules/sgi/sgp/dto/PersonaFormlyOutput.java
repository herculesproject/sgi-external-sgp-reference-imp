package org.crue.hercules.sgi.sgp.dto;

import java.io.Serializable;
import java.time.LocalDate;
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
public class PersonaFormlyOutput implements Serializable {

  private String personaId;
  private String apellidos;
  private String colectivoId;
  private String entidadRef;
  private String nombre;
  private String numeroDocumento;
  private boolean personalPropio;
  private String sexoId;
  private String tipoDocumentoId;

  // Datos academicos
  private LocalDate fechaObtencion;
  private String nivelAcademicoId;

  // Datos contacto
  private String ciudadContacto;
  private String codigoPostalContacto;
  private String comAutonomaContactoRef;
  private String direccionContacto;
  private List<EmailOutput> emails;
  private List<String> moviles;
  private String paisContactoRef;
  private String provinciaContactoRef;
  private List<String> telefonos;

  // Datos personales
  private String ciudadNacimiento;
  private String comAutonomaNacimientoRef;
  private LocalDate fechaNacimiento;
  private String paisNacimientoRef;

  // Vinculacion
  private String areaConocimientoRef;
  private String categoriaId;
  private String centroRef;
  private String departamentoId;
  private LocalDate fechaCategoria;
  private List<HistoricoCategoriaProfesionalOutput> historicoCategoriasProfesionales;

}