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
public class DireccionTesisOutput implements Serializable {
  private String id;
  private String personaRef;
  private String tituloTrabajo;
  private LocalDate fechaDefensa;
  private String alumno;
  private TipoProyectoOutput tipoProyecto;
  private String calificacionObtenida;
  private String coDirectorTesisRef;
  private boolean doctoradoEuropeo;
  private LocalDate fechaMencionDoctoradoEuropeo;
  private boolean mencionCalidad;
  private LocalDate fechaMencionCalidad;
  private boolean mencionInternacional;
  private boolean mencionIndustrial;

  private String url;
  private AreaCientificaOutput areaCientifica;
  private String resumen;

  @Data
  @EqualsAndHashCode(callSuper = false)
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class AreaCientificaOutput implements Serializable {
    private String id;
    private String nombre;
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class TipoProyectoOutput implements Serializable {
    private String id;
    private String nombre;
  }

}
