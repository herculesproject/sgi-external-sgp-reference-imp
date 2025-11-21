package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonaFormlyRequest {

  private String id;
  private String apellidos;
  private String colectivoId;
  private String entidadRef;
  private String nombre;
  private String numeroDocumento;
  private boolean personalPropio;
  private Sexo sexo;
  private TipoDocumento tipoDocumento;

  // Datos academicos
  private LocalDate fechaObtencion;
  private String nivelAcademicoId;

  // Datos contacto
  private String ciudadContacto;
  private String codigoPostalContacto;
  private String comAutonomaContactoRef;
  private String direccionContacto;
  private String emails;
  private String moviles;
  private String nombreViaContacto;
  private String paisContactoRef;
  private String provinciaContactoRef;
  private String telefonos;

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

  public Persona getPersona() {
    return Persona.builder()
        .id(id)
        .apellidos(apellidos)
        .colectivoId(colectivoId)
        .email(emails)
        .empresaRef(entidadRef)
        .nombre(nombre)
        .numeroDocumento(numeroDocumento)
        .personalPropio(personalPropio)
        .sexo(sexo)
        .tipoDocumento(tipoDocumento)
        .build();
  }

  public DatosAcademicos getDatosAcademicos() {
    if (fechaObtencion == null && nivelAcademicoId == null) {
      return null;
    }

    return DatosAcademicos.builder()
        .fechaObtencion(fechaObtencion)
        .nivelAcademico(nivelAcademicoId != null ? NivelAcademico.builder().id(nivelAcademicoId).build() : null)
        .build();
  }

  public DatosContacto getDatosContacto() {
    return DatosContacto.builder()
        .ciudadContacto(ciudadContacto)
        .codigoPostalContacto(codigoPostalContacto)
        .comAutonomaContactoRef(comAutonomaContactoRef)
        .direccionContacto(direccionContacto)
        .emails(emails)
        .moviles(moviles)
        .paisContactoRef(paisContactoRef)
        .provinciaContactoRef(provinciaContactoRef)
        .telefonos(telefonos)
        .build();
  }

  public DatosPersonales getDatosPersonales() {
    if (ciudadNacimiento == null
        && comAutonomaNacimientoRef == null
        && fechaNacimiento == null
        && paisNacimientoRef == null) {
      return null;
    }

    return DatosPersonales.builder()
        .ciudadNacimiento(ciudadNacimiento)
        .comAutonomaNacimientoRef(comAutonomaNacimientoRef)
        .fechaNacimiento(fechaNacimiento)
        .paisNacimientoRef(paisNacimientoRef)
        .build();
  }

  public Vinculacion getVinculacion() {
    return Vinculacion.builder()
        .areaConocimientoRef(areaConocimientoRef)
        .categoriaProfesional(categoriaId != null ? CategoriaProfesional.builder().id(categoriaId).build() : null)
        .centroRef(centroRef)
        .departamentoRef(departamentoId)
        .empresaRef(entidadRef)
        .fechaObtencionCategoria(fechaCategoria)
        .personalPropio(personalPropio)
        .build();
  }

}
