package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DatosPersonales.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosPersonales extends BaseEntity {

  protected static final String TABLE_NAME = "datos_personales";

  private static final String PERSONA_ID_COLUMN_NAME = "persona_id";
  private static final String FECHA_NACIMIENTO_COLUMN_NAME = "fecha_nacimiento";
  private static final String PAIS_NACIMIENTO_REF_COLUMN_NAME = "pais_nacimiento_ref";
  private static final String COM_AUTONOMA_NACIMIENTO_REF_COLUMN_NAME = "comunidad_nacimiento_ref";
  private static final String CIUDAD_NACIMIENTO_COLUMN_NAME = "ciudad_nacimiento";

  /** Persona Id */
  @Id
  @Column(name = PERSONA_ID_COLUMN_NAME)
  private String personaId;

  /** Fecha nacimiento */
  @Column(name = FECHA_NACIMIENTO_COLUMN_NAME)
  private LocalDate fechaNacimiento;

  /** Pais nacimiento */
  @Column(name = PAIS_NACIMIENTO_REF_COLUMN_NAME)
  private String paisNacimientoRef;

  /** Comunidad autonoma nacimiento */
  @Column(name = COM_AUTONOMA_NACIMIENTO_REF_COLUMN_NAME)
  private String comAutonomaNacimientoRef;

  /** Ciudad nacimiento */
  @Column(name = CIUDAD_NACIMIENTO_COLUMN_NAME)
  private String ciudadNacimiento;

}
