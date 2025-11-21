package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = HistoricoCategoriaProfesional.TABLE_NAME)
@IdClass(HistoricoCategoriaProfesionalId.class)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoCategoriaProfesional extends BaseEntity {

  protected static final String TABLE_NAME = "historico_categoria_profesional";

  private static final String PERSONA_ID_COLUMN_NAME = "persona_id";
  private static final String CATEGORIA_PROFESIONAL_COLUMN_NAME = "categoria_profesional_id";
  private static final String FECHA_OBTENCION_COLUMN_NAME = "fecha_obtencion";

  /** Persona Id */
  @Id
  @Column(name = PERSONA_ID_COLUMN_NAME)
  private String personaId;

  /** Categoria profesional */
  @Id
  @ManyToOne
  @JoinColumn(name = CATEGORIA_PROFESIONAL_COLUMN_NAME)
  private CategoriaProfesional categoriaProfesional;

  /** Fecha obtencion categoria */
  @Id
  @Column(name = FECHA_OBTENCION_COLUMN_NAME)
  private LocalDate fechaObtencion;

}
