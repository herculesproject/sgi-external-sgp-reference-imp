package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.crue.hercules.sgi.sgp.util.Constantes;

import io.micrometer.core.instrument.util.StringUtils;
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
public class PersonaFormlyResponse {

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
  private NivelAcademico nivelAcademico;

  // Datos contacto
  private String ciudadContacto;
  private String codigoPostalContacto;
  private String comAutonomaContactoRef;
  private String direccionContacto;
  private String emails;
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
  private List<HistoricoCategoriaProfesional> historicoCategoriasProfesionales;

  public PersonaFormlyResponse(Persona persona, DatosAcademicos datosAcademicos, DatosContacto datosContacto,
      DatosPersonales datosPersonales, Vinculacion vinculacion,
      List<HistoricoCategoriaProfesional> historicoCategoriasProfesionales) {

    this.setDatosPersona(persona);
    this.setDatosAcademicos(datosAcademicos);
    this.setDatosContacto(datosContacto);
    this.setDatosPersonales(datosPersonales);
    this.setVinculacion(vinculacion, historicoCategoriasProfesionales);
  }

  private void setDatosPersona(Persona persona) {
    this.id = persona.getId();
    this.apellidos = persona.getApellidos();
    this.colectivoId = persona.getColectivoId();
    this.emails = persona.getEmail();
    this.entidadRef = persona.getEmpresaRef();
    this.nombre = persona.getNombre();
    this.numeroDocumento = persona.getNumeroDocumento();
    this.personalPropio = persona.isPersonalPropio();
    this.sexo = persona.getSexo();
    this.tipoDocumento = persona.getTipoDocumento();
  }

  private void setDatosAcademicos(DatosAcademicos datosAcademicos) {
    if (datosAcademicos == null) {
      return;
    }

    this.nivelAcademico = datosAcademicos.getNivelAcademico();
    this.fechaObtencion = datosAcademicos.getFechaObtencion();
  }

  private void setDatosContacto(DatosContacto datosContacto) {
    if (datosContacto == null) {
      return;
    }

    this.ciudadContacto = datosContacto.getCiudadContacto();
    this.codigoPostalContacto = datosContacto.getCodigoPostalContacto();
    this.comAutonomaContactoRef = datosContacto.getComAutonomaContactoRef();
    this.direccionContacto = datosContacto.getDireccionContacto();
    this.paisContactoRef = datosContacto.getPaisContactoRef();
    this.provinciaContactoRef = datosContacto.getProvinciaContactoRef();

    this.moviles = new ArrayList<>();
    if (StringUtils.isNotBlank(datosContacto.getMoviles())) {
      moviles.addAll(Arrays.asList(datosContacto.getMoviles().split(Constantes.LIST_STRING_SEPARATOR)));
    }

    this.telefonos = new ArrayList<>();
    if (StringUtils.isNotBlank(datosContacto.getTelefonos())) {
      telefonos.addAll(Arrays.asList(datosContacto.getTelefonos().split(Constantes.LIST_STRING_SEPARATOR)));
    }
  }

  private void setDatosPersonales(DatosPersonales datosPersonales) {
    if (datosPersonales == null) {
      return;
    }

    this.fechaNacimiento = datosPersonales.getFechaNacimiento();
    this.paisNacimientoRef = datosPersonales.getPaisNacimientoRef();
    this.comAutonomaNacimientoRef = datosPersonales.getComAutonomaNacimientoRef();
    this.ciudadNacimiento = datosPersonales.getCiudadNacimiento();
  }

  private void setVinculacion(Vinculacion vinculacion,
      List<HistoricoCategoriaProfesional> historicoCategoriasProfesionales) {
    if (vinculacion == null) {
      return;
    }

    this.areaConocimientoRef = vinculacion.getAreaConocimientoRef();
    this.categoriaId = vinculacion.getCategoriaProfesional() != null ? vinculacion.getCategoriaProfesional().getId()
        : null;
    this.centroRef = vinculacion.getCentroRef();
    this.departamentoId = vinculacion.getDepartamentoRef();
    this.fechaCategoria = vinculacion.getFechaObtencionCategoria();
    this.historicoCategoriasProfesionales = historicoCategoriasProfesionales;
  }

}
