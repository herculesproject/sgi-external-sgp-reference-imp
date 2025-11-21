package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DatosAcademicos.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosAcademicos extends BaseEntity {

  protected static final String TABLE_NAME = "datos_academicos";

  private static final String PERSONA_ID_COLUMN_NAME = "persona_id";
  private static final String NIVEL_ACADEMICO_ID_COLUMN_NAME = "nivel_academico_id";
  private static final String FECHA_OBTENCION_COLUMN_NAME = "fecha_obtencion";

  /** Persona Id */
  @Id
  @Column(name = PERSONA_ID_COLUMN_NAME)
  private String personaId;

  /** Nivel academico */
  @ManyToOne
  @JoinColumn(name = NIVEL_ACADEMICO_ID_COLUMN_NAME)
  private NivelAcademico nivelAcademico;

  /** Fecha obtencion */
  @Column(name = FECHA_OBTENCION_COLUMN_NAME)
  private LocalDate fechaObtencion;

}
